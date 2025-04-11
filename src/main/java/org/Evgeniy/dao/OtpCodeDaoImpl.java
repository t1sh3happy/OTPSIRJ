package org.Evgeniy.dao;

import org.Evgeniy.model.OTPCode;
import org.Evgeniy.utill.DatabaseConnection;
import java.sql.*;
import org.Evgeniy.model.User;

public class OtpCodeDaoImpl implements OtpCodeDao {

    @Override
    public void addOTPCode(OTPCode otpCode) {
        String query = "INSERT INTO otpcodes (userid, code, status, createdat, operationid) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, otpCode.getUserId());
            statement.setString(2, otpCode.getCode());
            statement.setString(3, otpCode.getStatus());
            statement.setTimestamp(4, otpCode.getCreatedAt());
            statement.setString(5, otpCode.getOperationId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OTPCode getOTPCodeById(int id) {
        String query = "SELECT * FROM otp_codes WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new OTPCode(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getString("code"),
                            rs.getString("status"),
                            rs.getTimestamp("created_at"),
                            rs.getString("operation_id")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateStatus(int id, String status) {
        String query = "UPDATE otp_codes SET status = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, status);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OTPCode getActiveOTPByUserId(int userId) {
        String query = "SELECT * FROM otpcodes WHERE userid = ? AND status = 'ACTIVE'";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new OTPCode(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getString("code"),rs.getString("status"),
                            rs.getTimestamp("created_at"),
                            rs.getString("operation_id")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}