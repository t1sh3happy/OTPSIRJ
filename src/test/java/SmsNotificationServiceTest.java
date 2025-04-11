import org.Evgeniy.service.SmsNotificationService;
import org.junit.Before;
import org.junit.Test;

public class SmsNotificationServiceTest {

    private SmsNotificationService smsNotificationService;

    @Before
    public void setUp() {
        smsNotificationService = new SmsNotificationService();
    }

    @Test
    public void testSendCode() {
        // Подключение к SMPP серверу
        smsNotificationService.connect();

        // Тестовое отправление SMS на локальный номер
        smsNotificationService.sendCode("123456789", "123456");

        // Отключение от SMPP сервера
        smsNotificationService.disconnect();
    }
}