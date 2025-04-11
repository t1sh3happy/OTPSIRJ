import org.Evgeniy.service.TelegramService;

public class TelegramServiceTest {

    public static void main(String[] args) {
        TelegramService telegramService = new TelegramService();
        String otpCode = "123456"; // Ваш тестовый OTP код

        telegramService.sendOtpTelegram("Your OTP code is: " + otpCode);
    }
}