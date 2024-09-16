package testingtablesfolder;

import com.example.finalassignmentcab302.Tables.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private User user;

    @BeforeEach
    public void setUp() {
        // Initialize the User object before each test
        user = new User(1, "John", "Smith", "teddycreep", "password", "john.Smith@hotmail.com", "1234567890", "Middle Class");
    }

    @Test
    public void testGetId() {
        assertEquals(1, user.getId());
    }

    @Test
    public void testGetFirstName() {
        assertEquals("John", user.getFirstName());
    }

    @Test
    public void testSetFirstName() {
        user.setFirstName("Jane");
        assertEquals("Jane", user.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Smith", user.getLastName());
    }

    @Test
    public void testSetLastName() {
        user.setLastName("Last");
        assertEquals("Last", user.getLastName());
    }

    @Test
    public void testGetUserName() {
        assertEquals("teddycreep", user.getUserName());
    }

    @Test
    public void testSetUserName() {
        user.setUserName("janeSmith");
        assertEquals("janeSmith", user.getUserName());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password", user.getPassword());
    }

    @Test
    public void testSetPassword() {
        user.setPassword("newPassword456");
        assertEquals("newPassword456", user.getPassword());
    }

    @Test
    public void testGetEmail() {
        assertEquals("john.Smith@hotmail.com", user.getEmail());
    }

    @Test
    public void testSetEmail() {
        user.setEmail("jane.Smith@example.com");
        assertEquals("jane.Smith@example.com", user.getEmail());
    }

    @Test
    public void testGetPhoneNumber() {
        assertEquals("1234567890", user.getPhoneNumber());
    }

    @Test
    public void testSetPhoneNumber() {
        user.setPhoneNumber("0987654321");
        assertEquals("0987654321", user.getPhoneNumber());
    }

    @Test
    public void testGetEconomicClass() {
        assertEquals("Middle Class", user.getEconomicClass());
    }

    @Test
    public void testSetEconomicClass() {
        user.setEconomicClass("Upper Class");
        assertEquals("Upper Class", user.getEconomicClass());
    }

    @Test
    public void testToString() {
        String expected = "User{id=1, firstName='John', lastName='Smith', userName='teddycreep', email='john.Smith@hotmail.com', phoneNumber='1234567890', economicClass='Middle Class', password=password}";
        assertEquals(expected, user.toString());
    }
}
