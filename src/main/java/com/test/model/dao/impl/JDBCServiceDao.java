package com.test.model.dao.impl;

import com.test.model.dao.ServiceDao;
import com.test.model.dao.mapper.ServiceMapper;
import com.test.model.entity.Service;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static com.test.model.dao.impl.QueryConstants.FIND_ALL_SERVICES_PROP_NAME;
import static com.test.model.dao.impl.QueryConstants.QUERY_PROPERTIES_FILE_PATH;

public class JDBCServiceDao implements ServiceDao {

    private Connection connection;
    private String FIND_ALL_SERVICES;

    private static final Logger logger = Logger.getLogger(JDBCServiceDao.class);


    JDBCServiceDao(Connection connection) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(QUERY_PROPERTIES_FILE_PATH)){
            Properties prop = new Properties();
            prop.load(inputStream);

            FIND_ALL_SERVICES = prop.getProperty(FIND_ALL_SERVICES_PROP_NAME);

        } catch (Exception e) {
            logger.error("error while reading properties", e);
            e.printStackTrace();
        }
        this.connection = connection;
    }

    @Override
    public void create(Service entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Service> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Service> findAll() {
        try (Statement st = connection.createStatement()) {

            ResultSet rs = st.executeQuery(FIND_ALL_SERVICES);

            ServiceMapper serviceMapper = new ServiceMapper();
            List<Service> services = new ArrayList<>();

            while (rs.next()) {
                Service service = serviceMapper
                        .extractFromResultSet(rs);
                services.add(service);
            }
            return services;
        } catch (SQLException e) {
            logger.error("error while finding services", e);
            return new ArrayList<>();
        }
    }

    @Override
    public void update(Service entity) {
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
