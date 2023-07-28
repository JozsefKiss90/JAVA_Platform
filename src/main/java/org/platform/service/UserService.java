package org.platform.service;

import org.platform.database.Database;
import org.platform.data.User;

import java.sql.*;

public class UserService {

    private final Database database;

    public UserService(Database database) {
        this.database = database;
    }

    public void save(User user) {
        String template = "INSERT INTO user (username, email, password, role, created) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setString(1, user.username());
            statement.setString(2, user.email());
            statement.setString(3, user.password());
            statement.setString(4, user.role());
            statement.setDate(5, new java.sql.Date(user.created().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update operation
    public void updateUser(User user) {
        String template = "UPDATE user SET email = ?, password = ?, role = ?, created = ? WHERE username = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setString(1, user.email());
            statement.setString(2, user.password());
            statement.setString(3, user.role());
            statement.setDate(4, new java.sql.Date(user.created().getTime()));
            statement.setString(5, user.username());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUser(String username) {
        User user = null;
        String template = "SELECT * FROM user WHERE username = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"), // Reminder: storing passwords in plaintext is a bad practice. Always hash + salt your passwords!
                        resultSet.getString("role"),
                        resultSet.getDate("created")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    // Delete operation
    public void deleteUser(String username) {
        String template = "DELETE FROM user WHERE username = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            statement.setString(1, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
