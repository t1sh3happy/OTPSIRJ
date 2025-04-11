package org.Evgeniy.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OtpFileService {

    private static final Logger logger = LoggerFactory.getLogger(OtpFileService.class);

    // Путь к файлу, куда будут сохраняться OTP-коды
    private static final String FILEPATH = "otpcodes.txt";


    public void saveOtpCodeToFile(String otpCode) {
        try (FileWriter fw = new FileWriter(FILEPATH, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(otpCode); // Записываем в файл новую строку с OTP-кодом
            logger.info("Saved OTP code to file: {}", otpCode);

        } catch (IOException e) {
            logger.error("Failed to save OTP code to file", e);
        }
    }
}