package com.salon.model.dao;

import com.salon.model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByUsername(String username);
    void deleteByUsername(String username);
}
