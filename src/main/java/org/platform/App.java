package org.platform;

import org.platform.database.Database;
import org.platform.initalize.TableInitializer;
import org.platform.initalize.TableStatements;
import org.platform.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.util.Map;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserService userService, TableInitializer tableInitializer) {
        return args -> {
            tableInitializer.initialize();
        };
    }
}
