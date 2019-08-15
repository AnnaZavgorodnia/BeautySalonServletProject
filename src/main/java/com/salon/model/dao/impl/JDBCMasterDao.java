package com.salon.model.dao.impl;

import com.salon.model.dao.MasterDao;
import com.salon.model.dao.mapper.MasterMapper;
import com.salon.model.dao.mapper.ServiceMapper;
import com.salon.model.entity.Master;
import com.salon.model.entity.Service;
import com.salon.model.exception.NotUniqueEntity;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

import static com.salon.model.dao.impl.QueryConstants.*;

public class JDBCMasterDao implements MasterDao {

    private Connection connection;

    private static final Logger logger = Logger.getLogger(JDBCMasterDao.class);

    private String FIND_ALL_MASTERS;
    private String FIND_MASTER_BY_ID;
    private String INSERT_USER;
    private String INSERT_MASTER;
    private String INSERT_MASTER_SERVICE;

    JDBCMasterDao(Connection connection) {
        try (InputStream inputStream =
                     getClass().getClassLoader().getResourceAsStream(QUERY_PROPERTIES_FILE_PATH)){
            Properties prop = new Properties();
            prop.load(inputStream);

            FIND_ALL_MASTERS = prop.getProperty(FIND_ALL_MASTERS_PROP_NAME);
            FIND_MASTER_BY_ID = prop.getProperty(FIND_MASTER_BY_ID_PROP_NAME);
            INSERT_USER = prop.getProperty(INSERT_USER_PROP_NAME);
            INSERT_MASTER = prop.getProperty(INSERT_MASTER_PROP_NAME);
            INSERT_MASTER_SERVICE = prop.getProperty(INSERT_MASTER_SERVICE_PROP_NAME);

        } catch (Exception e) {
            logger.error("error while reading properties", e);
            e.printStackTrace();
        }
        this.connection = connection;
    }

    @Override
    public void create(Master entity) {
        try (PreparedStatement insertUserPs =
                     connection.prepareStatement(INSERT_USER,
                             Statement.RETURN_GENERATED_KEYS);
             PreparedStatement insertMasterPs =
                     connection.prepareStatement(INSERT_MASTER);
            PreparedStatement insertMasterServicePs =
                     connection.prepareStatement(INSERT_MASTER_SERVICE)) {

            connection.setAutoCommit(false);

            insertUserPs.setString(1, entity.getEmail());
            insertUserPs.setString(2, entity.getFullName());
            insertUserPs.setString(3, entity.getPassword());
            insertUserPs.setString(4, entity.getRole().name());
            insertUserPs.setString(5, entity.getUsername());

            int affectedRows = insertUserPs.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = insertUserPs.getGeneratedKeys()) {
                if (generatedKeys.next()) {

                    long id = generatedKeys.getLong(1);
                    
                    insertMasterPs.setLong(1, id);
                    insertMasterPs.setString(2,entity.getInstagram());
                    insertMasterPs.setString(3,entity.getPosition().name());
                    insertMasterPs.setString(4,entity.getImagePath());
                    System.out.println(entity.getFullNameUa());
                    insertMasterPs.setString(5, entity.getFullNameUa());
                    
                    insertMasterPs.executeUpdate();

                    for (Service service: entity.getServices()) {
                        insertMasterServicePs.setLong(1,id);
                        insertMasterServicePs.setLong(2,service.getId());
                        insertMasterServicePs.executeUpdate();
                    }

                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            connection.commit();

        } catch (SQLIntegrityConstraintViolationException e){
            logger.error("error while creating master", e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error("error while connection rollback", e);
            }
            throw new NotUniqueEntity(e);
        } catch (SQLException e) {
            logger.error("error while creating master", e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error("error while connection rollback", e);
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Master> findById(Long id) {

        try (PreparedStatement ps =
                     connection.prepareStatement(FIND_MASTER_BY_ID)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            MasterMapper masterMapper = new MasterMapper();
            ServiceMapper serviceMapper = new ServiceMapper();

            Map<Long, Master> masterCache = new HashMap<>();
            Map<Long, Service> serviceCache = new HashMap<>();

            List<Service> services = new ArrayList<>();

            Master master = null;
            while (rs.next()) {
                master = masterMapper.extractFromResultSet(rs);
                Service service = serviceMapper.extractFromResultSet(rs);

                master = masterMapper.makeUnique(masterCache, master);
                service = serviceMapper.makeUnique(serviceCache, service);

                services.add(service);
            }
            if(master != null){
                master.setServices(services);
                return Optional.of(master);
            }
            return Optional.empty();
        } catch (SQLException e) {
            logger.error("error while finding master", e);
            return Optional.empty();
        }
    }

    @Override
    public List<Master> findAll() {
        try (Statement st = connection.createStatement()) {

            ResultSet rs = st.executeQuery(FIND_ALL_MASTERS);

            MasterMapper masterMapper = new MasterMapper();
            List<Master> masters = new ArrayList<>();

            while (rs.next()) {
                Master master = masterMapper
                        .extractFromResultSet(rs);
                masters.add(master);
            }
            return masters;
        } catch (SQLException e) {
            logger.error("error while finding masters", e);
            return null;
        }
    }

    @Override
    public void update(Master entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("error while closing connection", e);
            throw new RuntimeException(e);
        }
    }
}
