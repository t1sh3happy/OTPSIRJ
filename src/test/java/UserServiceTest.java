import org.Evgeniy.service.UserService;
import org.Evgeniy.dao.UserDao;
import org.Evgeniy.model.User;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class UserServiceTest {

    private UserService userService;
    private UserDao userDao;

    @Before
    public void setUp() {
        userDao = mock(UserDao.class);
        userService = new UserService(userDao);
    }

    @Test
    public void testRegisterUser() {
        String username = "testuser";
        String password = "password";
        String role = "USER";

        userService.registerUser(username, password, role);
        verify(userDao).addUser(any(User.class));
    }

    @Test
    public void testAuthenticateUser() {
        String username = "testuser";
        String password = "password";

        User user = new User(1, username, userService.hashPassword(password), "USER");
        when(userDao.getUserByUsername(username)).thenReturn(user);

        assertTrue(userService.authenticate(username, password));
    }
}
