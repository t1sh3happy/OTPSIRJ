package org.Evgeniy.dao;

import org.Evgeniy.model.User;
import org.Evgeniy.utill.DatabaseConnection;

import java.sql.*;

public class UserDaoImpl implements UserDao {
    private final String url = "jdbc:postgresql://localhost:5432/otp_management";
    private final String user = "otp_user";
    private final String password = "your_password";

    public void addUser(User user) {
        String query = "INSERT INTO users (username, password_hash, role) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, this.user, this.password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPasswordHash());
            pstmt.setString(3, user.getRole());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(url, this.user, this.password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password_hash"),
                            rs.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void updateUser(User user) {
        String query = "UPDATE users SET username = ?, password_hash = ?, role = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPasswordHash());
            statement.setString(3, user.getRole());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}