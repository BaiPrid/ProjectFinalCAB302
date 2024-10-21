package OntrollerTests;

import com.example.finalassignmentcab302.dao.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserRegistrationControllerTest {

    private MockUserRegistrationController mockController;
    private UserDAO mockUserDAO;

    @BeforeEach
    public void setUp() {
        // Mocking the UserDAO
        mockUserDAO = Mockito.mock(UserDAO.class);

        // Pass the mocked DAO to the mock controller
        mockController = new MockUserRegistrationController(mockUserDAO);
    }

    @Test
    public void testHandleUserButtonActionWithEmptyFields() {
        // Simulate empty fields
        mockController.setFieldsForTesting("", "", "", "", "", "", null);

        // Simulate the button action
        mockController.handleUserButtonAction();

        // Verify that the mock alert message is set correctly
        assertEquals("Mock alert: Fields are empty.", mockController.getMockAlertMessage());
    }

    @Test
    public void testHandleUserButtonActionWithValidFields() {
        // Set valid values in the fields
        mockController.setFieldsForTesting("ValidFirstName", "ValidLastName", "ValidUser", "ValidPass", "validEmail@test.com", "1234567890", "Middle");

        // Simulate the button action
        mockController.handleUserButtonAction();

        // Verify that the correct message is set for valid fields
        assertEquals("Fields are valid.", mockController.getMockAlertMessage());
    }

    @Test
    public void testPhoneNumberIsInvalid() {
        // Set invalid phone number
        mockController.setFieldsForTesting("ValidFirstName", "ValidLastName", "ValidUser", "ValidPass", "validEmail@test.com", "invalidPhone", "Middle");

        // Simulate the button action
        mockController.handleUserButtonAction();

        // Verify that the phone number validation fails
        assertEquals("Mock alert: Invalid phone number.", mockController.getMockAlertMessage());
    }

    @Test
    public void testUsernameIsUnique() {
        // Mock behavior to return true, meaning the username exists in the database
        when(mockUserDAO.CheckUsername("existingUser")).thenReturn(true);

        // Validate that username uniqueness check works (username is NOT unique)
        assertFalse(mockController.isUsernameUnique("existingUser"));

        // Mock behavior to return false, meaning the username does not exist in the database
        when(mockUserDAO.CheckUsername("newUser")).thenReturn(false);

        // Validate that username uniqueness check works (username is unique)
        assertTrue(mockController.isUsernameUnique("newUser"));
    }

    @Test
    public void testEmailIsUnique() {
        // Mock behavior to return true, meaning the email exists in the database
        when(mockUserDAO.checkEmail("existingemail@example.com")).thenReturn(true);

        // Validate that email uniqueness check works (email is NOT unique)
        assertFalse(mockController.isEmailUnique("existingemail@example.com"));

        // Mock behavior to return false, meaning the email does not exist in the database
        when(mockUserDAO.checkEmail("newemail@example.com")).thenReturn(false);

        // Validate that email uniqueness check works (email is unique)
        assertTrue(mockController.isEmailUnique("newemail@example.com"));
    }
}
