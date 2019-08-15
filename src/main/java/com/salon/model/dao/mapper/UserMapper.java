package com.salon.model.dao.mapper;

import com.salon.model.entity.Role;
import com.salon.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("c_id"));
        user.setUsername(rs.getString("c_username"));
        user.setFullName(rs.getString("c_full_name"));
        user.setEmail(rs.getString("c_email"));
        user.setPassword(rs.getString("c_password"));
        user.setRole(Role.valueOf(rs.getString("c_role")));
        return user;
    }

    @Override
    public User makeUnique(Map<Long, User> cache, User item) {
        cache.putIfAbsent(item.getId(), item);
        return cache.get(item.getId());
    }
}
