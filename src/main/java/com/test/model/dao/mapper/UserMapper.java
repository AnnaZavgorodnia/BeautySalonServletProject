package com.test.model.dao.mapper;

import com.test.model.entity.Role;
import com.test.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setFullName(rs.getString("full_name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setRole(Role.valueOf(rs.getString("role")));
        return user;
    }

    @Override
    public User makeUnique(Map<Long, User> cache, User item) {
        cache.putIfAbsent(item.getId(), item);
        return cache.get(item.getId());
    }
}
