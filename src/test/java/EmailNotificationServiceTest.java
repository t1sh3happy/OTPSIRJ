import org.Evgeniy.service.EmailNotificationService;
import org.junit.Before;
import org.junit.Test;
import java.util.Properties;

public class EmailNotificationServiceTest {

    private EmailNotificationService emailService;

    @Before
    public void setUp() {
        emailService = new EmailNotificationService();
    }

    @Test
    public void testSendCodeEmail() {
        // Используйте тестовый адрес email
        String testEmailAddress = "test@example.com";
        emailService.sendOTP(testEmailAddress, "123456");
        // Добавьте проверки, если ваши тестовые SMTP или email API могут предоставить обратную связь
    }
}