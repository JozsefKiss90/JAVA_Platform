package org.platform.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Database {

    private final DataSource dataSource;

    public Database(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
            System.err.println("Could not create database connection.");
            throw new RuntimeException(ex);
        }
    }
}
