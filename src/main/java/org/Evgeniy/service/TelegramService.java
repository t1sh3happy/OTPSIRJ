package org.Evgeniy.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class TelegramService {

    private static final String BOTTOKEN = "7354885142:AAFB57kxGe3MdT2wwbyKrsc77bulksTkvA0"; // Укажите токен вашего бота
    private static final String CHATID = "679296241"; // Укажите ваш chat ID

    public void sendOtpTelegram(String messageText) {
        String url = String.format(
                "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s",
                BOTTOKEN, CHATID, urlEncode(messageText)
        );

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            httpClient.execute(request);
            System.out.println("Message sent to Telegram successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String urlEncode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}