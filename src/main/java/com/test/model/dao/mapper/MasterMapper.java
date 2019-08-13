package com.test.model.dao.mapper;

import com.test.model.entity.Master;
import com.test.model.entity.Position;
import com.test.model.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class MasterMapper implements ObjectMapper<Master>{
    @Override
    public Master extractFromResultSet(ResultSet rs) throws SQLException {
        Master master = new Master();
        master.setId(rs.getLong("m_id"));
        master.setUsername(rs.getString("m_username"));
        master.setFullName(rs.getString("m_full_name"));
        master.setEmail(rs.getString("m_email"));
        master.setPassword(rs.getString("m_password"));
        master.setRole(Role.valueOf(rs.getString("m_role")));
        master.setPosition(Position.valueOf(rs.getString("m_position")));
        master.setInstagram(rs.getString("m_instagram"));
        master.setImagePath(rs.getString("m_image_path"));
        master.setFullNameUa(rs.getString("m_full_name_ua"));
        return master;
    }

    @Override
    public Master makeUnique(Map<Long, Master> cache, Master item) {
        cache.putIfAbsent(item.getId(), item);
        return cache.get(item.getId());
    }
}
