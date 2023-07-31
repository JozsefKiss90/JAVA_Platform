package org.platform.initalize;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TableInitializer {
    private final JdbcTemplate jdbcTemplate;
    private final Map<String, String> tables;

    @Autowired
    public TableInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.tables = Map.of("user", TableStatements.USER);
    }

    public void initialize() {
        tables.entrySet().stream()
                .filter(table -> !exists(table))
                .forEach(table -> create(table));
    }

    private boolean exists(Map.Entry<String, String> table) {
        String tableName = table.getKey();
        String sql = "SELECT count(*) FROM information_schema.tables WHERE table_name = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, tableName);
        return count != null && count > 0;
    }

    private void create(Map.Entry<String, String> table) {
        jdbcTemplate.execute(table.getValue());
        System.out.println("Table created = " + table.getKey());
    }
}