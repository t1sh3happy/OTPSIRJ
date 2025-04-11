package org.Evgeniy.model;

import java.sql.Timestamp;

public class OTPCode {
    private int id;
    private int userId;
    private String code;
    private String status;
    private Timestamp createdAt;
    private String operationId;

    public OTPCode(int id, int userId, String code, String status, Timestamp createdAt, String operationId) {
        this.id = id;
        this.userId = userId;
        this.code = code;
        this.status = status;
        this.createdAt = createdAt;
        this.operationId = operationId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }
}