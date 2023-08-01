package org.platform;

import org.platform.controller.UserController;
import org.platform.database.Database;
import org.platform.initalize.TableInitializer;
import org.platform.service.UserService;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class AppConfig {

    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/java_platform");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        return dataSource;
    }

    public Database database() {
        return new Database(dataSource());
    }

    public TableInitializer tableInitializer() {
        return new TableInitializer(database());
    }

    public UserService userService() {
        return new UserService(database());
    }

    public UserController userController() {
        return new UserController(userService());
    }
}
