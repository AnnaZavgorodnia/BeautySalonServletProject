package com.salon.model.dao.mapper;

import com.salon.model.entity.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ServiceMapper implements ObjectMapper<Service>{
    @Override
    public Service extractFromResultSet(ResultSet rs) throws SQLException {
        Service service = new Service();
        service.setId(rs.getLong("s_id"));
        service.setName(rs.getString("s_name"));
        service.setNameUa(rs.getString("s_name_ua"));
        service.setPrice(rs.getInt("s_price"));
        return service;
    }

    @Override
    public Service makeUnique(Map<Long, Service> cache, Service item) {
        cache.putIfAbsent(item.getId(), item);
        return cache.get(item.getId());
    }
}
