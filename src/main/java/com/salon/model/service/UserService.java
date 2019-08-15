package com.salon.model.service;

import com.salon.model.dao.DaoFactory;
import com.salon.model.dao.UserDao;
import com.salon.model.entity.User;

import java.util.List;
import java.util.Optional;

public class UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<User> getAllUsers(){
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findAll();
        }
    }

    public Optional<User> getUserByUsername(String username){
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findByUsername(username);
        }
    }

    public void createUser(User user) {
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.create(user);
        }
    }

    public void deleteByUsername(String username){
        try (UserDao dao = daoFactory.createUserDao()) {
            dao.deleteByUsername(username);
        }
    }
}
