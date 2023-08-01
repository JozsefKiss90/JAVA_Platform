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
        // Configure your datasource here (e.g., URL, username, password, driver class name)
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
