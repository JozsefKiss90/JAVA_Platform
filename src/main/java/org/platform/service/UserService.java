package org.platform.service;

import org.platform.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

@Service
public class UserService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Create operation
    public void save(User user) {
        String sql = "INSERT INTO users (username, email, password, role, created) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.username(), user.email(), user.password(), user.role(), new Date(user.created().getTime()));
    }

    // Read operation
    public User getUser(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username}, new UserRowMapper());
    }

    // Update operation
    public void updateUser(User user) {
        String sql = "UPDATE users SET email = ?, password = ?, role = ?, created = ? WHERE username = ?";
        jdbcTemplate.update(sql, user.email(), user.password(), user.role(), new Date(user.created().getTime()), user.username());
    }

    // Delete operation
    public void deleteUser(String username) {
        String sql = "DELETE FROM users WHERE username = ?";
        jdbcTemplate.update(sql, username);
    }

    class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password"), // Storing passwords in plaintext is a bad practice. Always hash + salt your passwords!
                    rs.getString("role"),
                    rs.getDate("created")
            );
        }
    }
}
