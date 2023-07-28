package org.platform;

import org.platform.database.Database;
import org.platform.initalize.TableInitializer;
import org.platform.initalize.TableStatements;
import org.platform.service.UserService;

import java.util.Map;

public class App {
    public static void main(String[] args) {
        Database database = new Database(
                "jdbc:postgresql://localhost:5432/invoice",
                "postgres",
                "postgres");
        Map<String, String> tables = Map.of(
                "user", TableStatements.USER
        );

        TableInitializer tableInitializer = new TableInitializer(database, tables);
        tableInitializer.initialize();

        UserService userService = new UserService(database);
    }
}