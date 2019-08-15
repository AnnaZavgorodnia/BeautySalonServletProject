package com.salon.model.dao.impl;

import com.salon.model.dao.AppointmentDao;
import com.salon.model.dao.mapper.*;
import com.salon.model.entity.*;
import com.salon.model.exception.NotUniqueEntity;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

import static com.salon.model.dao.impl.QueryConstants.*;

public class JDBCAppointmentDao implements AppointmentDao {

    private Connection connection;

    private static final Logger logger = Logger.getLogger(JDBCAppointmentDao.class);

    private String FIND_APPOINTMENTS_BY_CLIENT_USERNAME;
    private String DELETE_APPOINTMENT_BY_ID;
    private String FIND_APPOINTMENTS_BY_MASTER_USERNAME;
    private String FIND_ALL_APPOINTMENTS;
    private String FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE;
    private String INSERT_APPOINTMENT;

    JDBCAppointmentDao(Connection connection) {
        try (InputStream inputStream =
                     getClass().getClassLoader().getResourceAsStream(QUERY_PROPERTIES_FILE_PATH)){
            Properties prop = new Properties();
            prop.load(inputStream);

            FIND_APPOINTMENTS_BY_CLIENT_USERNAME = prop.getProperty(FIND_APPOINTMENTS_BY_CLIENT_USERNAME_PROP_NAME);
            DELETE_APPOINTMENT_BY_ID = prop.getProperty(DELETE_APPOINTMENT_BY_ID_PROP_NAME);
            FIND_APPOINTMENTS_BY_MASTER_USERNAME = prop.getProperty(FIND_APPOINTMENTS_BY_MASTER_USERNAME_PROP_NAME);
            FIND_ALL_APPOINTMENTS = prop.getProperty(FIND_ALL_APPOINTMENTS_PROP_NAME);
            FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE = prop.getProperty(FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE_PROP_NAME);
            INSERT_APPOINTMENT = prop.getProperty(INSERT_APPOINTMENT_PROP_NAME);

        } catch (Exception e) {
            logger.error("error while reading properties", e);
            e.printStackTrace();
        }
        this.connection = connection;
    }

    @Override
    public List<Appointment> findAllByClientOrMaster_Username(String username, Role role) {

        try (PreparedStatement ps =
                     connection.prepareStatement(
                             role == Role.CLIENT
                                     ? FIND_APPOINTMENTS_BY_CLIENT_USERNAME
                                     : FIND_APPOINTMENTS_BY_MASTER_USERNAME)) {

            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();

            return extractListAppointments(rs);

        } catch (SQLException e) {
            logger.error("error while finding appointments", e);
            return new ArrayList<>();
        }
    }

    @Override
    public void create(Appointment entity) {
        try(PreparedStatement ps =
                     connection.prepareStatement(INSERT_APPOINTMENT)){

            ps.setString(1,entity.getAppDate().toString());
            ps.setString(2,entity.getAppTime().toString());
            ps.setLong(3,entity.getClient().getId());
            ps.setLong(4,entity.getMaster().getId());
            ps.setLong(5,entity.getService().getId());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating appointment failed, no rows affected.");
            }

        }catch (SQLIntegrityConstraintViolationException e){
            logger.warn("appointment duplicate value " + entity);
            throw new NotUniqueEntity(e);
        } catch (SQLException e) {
            logger.error("error while creating appointment", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Appointment> findAllByMasterIdAndDate(Long masterId, LocalDate date) {
        try (PreparedStatement ps =
                     connection.prepareStatement(FIND_APPOINTMENTS_BY_MASTER_ID_AND_DATE)) {

            ps.setLong(1,masterId);
            ps.setString(2,date.toString());

            ResultSet rs = ps.executeQuery();

            AppointmentMapper appMapper = new AppointmentMapper();

            List<Appointment> apps = new ArrayList<>();

            while (rs.next()) {
                Appointment app = appMapper
                        .extractFromResultSet(rs);
                apps.add(app);
            }

            return apps;

        } catch (SQLException e) {
            logger.error("error while finding appointment", e);
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Appointment> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Appointment> findAll() {
        try (Statement st = connection.createStatement()) {

            ResultSet rs = st.executeQuery(FIND_ALL_APPOINTMENTS);
            return extractListAppointments(rs);

        } catch (SQLException e) {
            logger.error("error while finding appointment", e);
            return new ArrayList<>();
        }
    }

    @Override
    public void update(Appointment entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_APPOINTMENT_BY_ID)) {

            ps.setLong(1,id);
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.error("error while deleting appointment", e);
        }
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

    private List<Appointment> extractListAppointments(ResultSet rs) throws SQLException {

        AppointmentMapper appMapper = new AppointmentMapper();
        MasterMapper masterMapper = new MasterMapper();
        ServiceMapper serviceMapper = new ServiceMapper();
        UserMapper userMapper = new UserMapper();

        Map<Long, Master> masterCache = new HashMap<>();
        Map<Long, Service> serviceCache = new HashMap<>();
        Map<Long, User> clientCache = new HashMap<>();

        List<Appointment> apps = new ArrayList<>();

        while (rs.next()) {
            Appointment app = appMapper
                    .extractFromResultSet(rs);
            Master master = masterMapper.extractFromResultSet(rs);
            Service service = serviceMapper.extractFromResultSet(rs);
            User client = userMapper.extractFromResultSet(rs);
            master = masterMapper.makeUnique(masterCache, master);
            service = serviceMapper.makeUnique(serviceCache, service);
            client = userMapper.makeUnique(clientCache, client);
            app.setMaster(master);
            app.setService(service);
            app.setClient(client);
            apps.add(app);
        }

        return apps;
    }
}
