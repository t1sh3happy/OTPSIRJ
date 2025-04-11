package org.Evgeniy.model;

public class OTPConfig {
    private int id;
    private int codeLength;
    private int expirationMinutes;

    public OTPConfig(int id, int codeLength, int expirationMinutes) {
        this.id = id;
        this.codeLength = codeLength;
        this.expirationMinutes = expirationMinutes;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(int codeLength) {
        this.codeLength = codeLength;
    }

    public int getExpirationMinutes() {
        return expirationMinutes;
    }

    public void setExpirationMinutes(int expirationMinutes) {
        this.expirationMinutes = expirationMinutes;
    }
}