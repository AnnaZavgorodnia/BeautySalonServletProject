package com.test.model.dao.mapper;

import com.test.model.entity.Appointment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AppointmentMapper implements ObjectMapper<Appointment> {
    @Override
    public Appointment extractFromResultSet(ResultSet rs) throws SQLException {
        Appointment app = new Appointment();
        app.setId(rs.getLong("app_id"));
        app.setAppDate(rs.getDate("app_date").toLocalDate());
        app.setAppTime(rs.getTime("app_time").toLocalTime());
        return app;
    }

    @Override
    public Appointment makeUnique(Map<Long, Appointment> cache, Appointment item) {
        cache.putIfAbsent(item.getId(), item);
        return cache.get(item.getId());
    }
}
