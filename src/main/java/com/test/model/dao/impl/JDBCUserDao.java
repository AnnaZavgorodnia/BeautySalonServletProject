package com.test.model.dao.impl;

import com.test.model.dao.UserDao;
import com.test.model.dao.mapper.UserMapper;
import com.test.model.entity.User;
import com.test.model.exception.NotUniqueEntity;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static com.test.model.dao.impl.QueryConstants.*;

public class JDBCUserDao implements UserDao {

    private Connection connection;

    private static final Logger logger = Logger.getLogger(JDBCUserDao.class);

    private String FIND_USER_BY_USERNAME;
    private String FIND_ALL_USERS;
    private String INSERT_USER;
    private String INSERT_CLIENT;

    JDBCUserDao(Connection connection) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(QUERY_PROPERTIES_FILE_PATH)){
            Properties prop = new Properties();
            prop.load(inputStream);

            FIND_USER_BY_USERNAME = prop.getProperty(FIND_USER_BY_USERNAME_PROP_NAME);
            FIND_ALL_USERS =  prop.getProperty(FIND_ALL_USERS_PROP_NAME);
            INSERT_USER = prop.getProperty(INSERT_USER_PROP_NAME);
            INSERT_CLIENT = prop.getProperty(INSERT_CLIENT_PROP_NAME);

        } catch (Exception e) {
            logger.error("error while reading properties", e);
            e.printStackTrace();
        }

        this.connection = connection;
    }

    @Override
    public Optional<User> findByUsername(String username) {

        try (PreparedStatement ps = connection.prepareStatement(FIND_USER_BY_USERNAME)) {

            ps.setString(1,username);

            ResultSet rs = ps.executeQuery();

            UserMapper userMapper = new UserMapper();

            if(rs.next()) {
                return Optional.of(userMapper
                        .extractFromResultSet(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            logger.error("error while finding user by username", e);
            return Optional.empty();
        }
    }

    @Override
    public void create(User entity) {
        try (PreparedStatement insertUserPs =
                     connection.prepareStatement(INSERT_USER,
                             Statement.RETURN_GENERATED_KEYS);
             PreparedStatement insertClientPs =
                     connection.prepareStatement(INSERT_CLIENT)) {

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
                    insertClientPs.setLong(1, id);
                    insertClientPs.executeUpdate();

                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            connection.commit();

        } catch (SQLIntegrityConstraintViolationException e){
            logger.error("error while creating user", e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error("error while connection rollback", ex);
            }
            throw new NotUniqueEntity(e);
        } catch (SQLException e) {
            logger.error("error while creating user", e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error("error while connection rollback", ex);
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        try (Statement st = connection.createStatement()) {

            ResultSet rs = st.executeQuery(FIND_ALL_USERS);

            UserMapper userMapper = new UserMapper();
            List<User> users = new ArrayList<>();

            while (rs.next()) {
                User user = userMapper
                        .extractFromResultSet(rs);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            logger.error("error while finding users", e);
            return new ArrayList<>();
        }
    }

    @Override
    public void update(User entity) {
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
