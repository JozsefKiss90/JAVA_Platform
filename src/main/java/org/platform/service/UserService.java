package org.platform.service;

import org.platform.data.UserDTO;
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

    public void save(UserDTO userDTO) {
        String sql = "INSERT INTO users (username, email, password, role, created) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, userDTO.username(), userDTO.email(), userDTO.password(), userDTO.role(), new Date(userDTO.created().getTime()));
    }

    public UserDTO getUser(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username}, new UserRowMapper());
    }

    public void updateUser(String username, UserDTO userDTO) {
        String sql = "UPDATE users SET email = ?, password = ?, role = ?, created = ? WHERE username = ?";
        jdbcTemplate.update(sql, userDTO.email(), userDTO.password(), userDTO.role(), new Date(userDTO.created().getTime()), userDTO.username());
    }

    public void deleteUser(String username) {
        String sql = "DELETE FROM users WHERE username = ?";
        jdbcTemplate.update(sql, username);
    }

    class UserRowMapper implements RowMapper<UserDTO> {
        @Override
        public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new UserDTO(
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password"), // Storing passwords in plaintext is a bad practice. Always hash + salt your passwords!
                    rs.getString("role"),
                    rs.getDate("created")
            );
        }
    }
}
