package org.platform;

import org.platform.controller.UserController;
import org.platform.database.Database;
import org.platform.initalize.TableInitializer;
import org.platform.service.UserService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        AppConfig appConfig = context.getBean(AppConfig.class);

        DataSource dataSource = appConfig.dataSource();
        Database database = new Database(dataSource);
        TableInitializer tableInitializer = new TableInitializer(database);
        tableInitializer.initialize();
        UserService userService = new UserService(database);
        UserController userController = new UserController(userService);

        // You can now send requests through userController
    }
}
