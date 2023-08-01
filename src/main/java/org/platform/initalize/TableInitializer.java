package org.platform.initalize;

import org.platform.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TableInitializer {
    private final Database database;
    private final Map<String, String> tables;

    public TableInitializer(Database database) {
        this.database = database;
        this.tables = Map.of("user", TableStatements.USER);
    }

    public void initialize() {
        tables.entrySet().stream()
                .filter(table -> !exists(table))
                .forEach(table -> create(table));
    }

    private boolean exists(Map.Entry<String, String> table) {
        try (Connection conn = database.getConnection()) {
            String tableName = table.getKey();
            String sql = "SELECT count(*) FROM information_schema.tables WHERE table_name = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tableName);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void create(Map.Entry<String, String> table) {
        try (Connection conn = database.getConnection()) {
            conn.createStatement().execute(table.getValue());
            System.out.println("Table created = " + table.getKey());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
