package org.Evgeniy.service;

public interface NotificationService {
    void sendOTP(String destination, String otpCode);
}
