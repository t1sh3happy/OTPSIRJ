import org.Evgeniy.service.OTPService;
import org.Evgeniy.dao.OtpCodeDao;
import org.Evgeniy.model.OTPCode;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class OTPServiceTest {

    private OTPService otpService;
    private OtpCodeDao mockedOtpCodeDao;

    @Before
    public void setUp() {
        mockedOtpCodeDao = mock(OtpCodeDao.class);
        otpService = new OTPService(mockedOtpCodeDao); // Передаем mock объект в OTPService
    }

    @Test
    public void testGenerateOTP() {
        OTPCode otpCode = otpService.generateOTP(1);
        assertNotNull(otpCode);
        assertEquals("ACTIVE", otpCode.getStatus());
    }

    @Test
    public void testValidateOTP() {
        OTPCode otpCode = new OTPCode(1, 1, "123456", "ACTIVE", null, null);
        when(mockedOtpCodeDao.getActiveOTPByUserId(1)).thenReturn(otpCode);

        assertTrue(otpService.validateOTP(1, "123456"));
    }
}
