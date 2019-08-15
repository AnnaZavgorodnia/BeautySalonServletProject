package com.salon.model.service;

import com.salon.model.dao.DaoFactory;
import com.salon.model.dao.ServiceDao;
import com.salon.model.entity.Service;

import java.util.List;

public class SalonServicesService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Service> findAll() {
        try (ServiceDao dao = daoFactory.createServiceDao()) {
            return dao.findAll();
        }
    }
}
