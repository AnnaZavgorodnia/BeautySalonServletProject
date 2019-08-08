package com.test.model.dao.mapper;

import com.test.model.entity.Client;
import com.test.model.entity.Position;
import com.test.model.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ClientMapper implements ObjectMapper<Client> {
    @Override
    public Client extractFromResultSet(ResultSet rs) throws SQLException {
        Client client = new Client();
        client.setId(rs.getLong("c_id"));
        client.setUsername(rs.getString("c_username"));
        client.setFullName(rs.getString("c_full_name"));
        client.setEmail(rs.getString("c_email"));
        client.setPassword(rs.getString("c_password"));
        client.setRole(Role.valueOf(rs.getString("c_role")));
        return client;
    }

    @Override
    public Client makeUnique(Map<Long, Client> cache, Client item) {
        cache.putIfAbsent(item.getId(), item);
        return cache.get(item.getId());
    }
}
