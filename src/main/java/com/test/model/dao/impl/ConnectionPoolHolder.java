package com.test.model.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

class ConnectionPoolHolder {

    private static volatile DataSource dataSource;
    private static String url;
    private static String username;
    private static String password;

    static{
        try (InputStream inputStream =
                     ConnectionPoolHolder.class.getClassLoader().getResourceAsStream("database/connection.properties")){
            Properties prop = new Properties();
            prop.load(inputStream);

            url = prop.getProperty("connection.url");
            username = prop.getProperty("connection.username");
            password = prop.getProperty("connection.password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static DataSource getDataSource(){

        if (dataSource == null){
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl(url);
                    ds.setUsername(username);
                    ds.setPassword(password);
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;

    }
}
