package com.test.model.service;

import com.test.model.dao.DaoFactory;
import com.test.model.dao.MasterDao;
import com.test.model.dao.UserDao;
import com.test.model.entity.Master;
import com.test.model.entity.User;

import java.util.List;
import java.util.Optional;

public class MasterService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Master> getAllMasters(){
        try (MasterDao dao = daoFactory.createMasterDao()) {
            return dao.findAll();
        }
    }

    public Optional<Master> findById(Long id) {
        try (MasterDao dao = daoFactory.createMasterDao()) {
            return dao.findById(id);
        }
    }

    public void create(Master master) {
        try (MasterDao dao = daoFactory.createMasterDao()) {
            dao.create(master);
        }
    }
}
