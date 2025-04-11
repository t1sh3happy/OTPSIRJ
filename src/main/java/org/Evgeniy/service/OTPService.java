package org.Evgeniy.service;

import org.Evgeniy.dao.OtpCodeDao;
import org.Evgeniy.model.OTPCode;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class OTPService {

    private final OtpCodeDao otpCodeDao;
    private final String filePath = "otp_codes.txt"; // Путь к файлу для хранения OTP


    public OTPService(OtpCodeDao otpCodeDao) {
        this.otpCodeDao = otpCodeDao;
    }

    public OTPCode generateOTP(int userId) {

        String otpCodeStr = String.format("%06d", (int) (Math.random() * 1000000));

        OTPCode otp = new OTPCode(0, userId, otpCodeStr, "ACTIVE", new Timestamp(System.currentTimeMillis()), null);
        otpCodeDao.addOTPCode(otp);


        saveOtpToFile(otp);

        return otp;
    }

    public boolean validateOTP(int userId, String otpCode) {
        OTPCode storedOtp = otpCodeDao.getActiveOTPByUserId(userId);
        if (storedOtp != null && storedOtp.getCode().equals(otpCode)) {
            otpCodeDao.updateStatus(storedOtp.getId(), "USED");
            return true;
        }
        return false;
    }

    private void saveOtpToFile(OTPCode otp) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write("OTP Code: " + otp.getCode() + ", User ID: " + otp.getUserId() + ", Status: " + otp.getStatus() + ", Created At: " + otp.getCreatedAt() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
