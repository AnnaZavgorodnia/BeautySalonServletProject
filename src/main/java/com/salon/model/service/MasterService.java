package com.salon.model.service;

import com.salon.model.dao.DaoFactory;
import com.salon.model.dao.MasterDao;
import com.salon.model.entity.Master;

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
