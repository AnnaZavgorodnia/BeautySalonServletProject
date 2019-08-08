package com.test.model.service;

import com.test.model.dao.DaoFactory;
import com.test.model.dao.ServiceDao;
import com.test.model.entity.Service;

import java.util.List;

public class SalonServicesService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Service> findAll() {
        try (ServiceDao dao = daoFactory.createServiceDao()) {
            return dao.findAll();
        }
    }
}
