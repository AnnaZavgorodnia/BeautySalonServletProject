package com.test.model.dao.impl;

import com.test.model.dao.UserDao;
import com.test.model.dao.mapper.UserMapper;
import com.test.model.entity.User;
import com.test.model.exception.UserAlreadyExistsException;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static com.test.model.dao.impl.QueryConstants.*;

public class JDBCUserDao implements UserDao {

    private Connection connection;

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
            e.printStackTrace();
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
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new UserAlreadyExistsException(e);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
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
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
