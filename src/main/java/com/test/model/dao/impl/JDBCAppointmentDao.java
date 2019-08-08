package com.test.model.dao.impl;

import com.test.model.dao.AppointmentDao;
import com.test.model.dao.mapper.*;
import com.test.model.entity.*;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

import static com.test.model.dao.impl.QueryConstants.*;

public class JDBCAppointmentDao implements AppointmentDao {

    private Connection connection;

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
            e.printStackTrace();
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

        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void update(Appointment entity) {

    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_APPOINTMENT_BY_ID)) {

            ps.setLong(1,id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Appointment> extractListAppointments(ResultSet rs) throws SQLException {

        AppointmentMapper appMapper = new AppointmentMapper();
        MasterMapper masterMapper = new MasterMapper();
        ServiceMapper serviceMapper = new ServiceMapper();
        ClientMapper clientMapper = new ClientMapper();

        Map<Long, Master> masterCache = new HashMap<>();
        Map<Long, Service> serviceCache = new HashMap<>();
        Map<Long, Client> clientCache = new HashMap<>();

        List<Appointment> apps = new ArrayList<>();

        while (rs.next()) {
            Appointment app = appMapper
                    .extractFromResultSet(rs);
            Master master = masterMapper.extractFromResultSet(rs);
            Service service = serviceMapper.extractFromResultSet(rs);
            Client client = clientMapper.extractFromResultSet(rs);
            master = masterMapper.makeUnique(masterCache, master);
            service = serviceMapper.makeUnique(serviceCache, service);
            client = clientMapper.makeUnique(clientCache, client);
            app.setMaster(master);
            app.setService(service);
            app.setClient(client);
            apps.add(app);
        }

        return apps;
    }

    private Appointment extractAppointment(ResultSet rs){
        return null;
    }
}
