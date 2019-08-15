package com.salon.model.dao;

import com.salon.model.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory{

    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract MasterDao createMasterDao();
    public abstract AppointmentDao createAppointmentDao();
    public abstract ServiceDao createServiceDao();

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
