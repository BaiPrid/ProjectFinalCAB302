package OntrollerTests;

import com.example.finalassignmentcab302.dao.UserDAO;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class UserRegistrationControllerTest {

    private MockUserRegistrationController mockController;
    private UserDAO mockUserDAO;

    @BeforeAll
    public static void initToolkit() throws InterruptedException {
        JavaFxInitializer.initToolkit(); // Use the singleton utility class to initialize JavaFX toolkit
    }


    @BeforeEach
    public void setUp() {
        // Mocking the UserDAO
        mockUserDAO = Mockito.mock(UserDAO.class);

        // Use real JavaFX components instead of mocking them
        TextField firstName = new TextField();
        TextField lastName = new TextField();
        TextField userName = new TextField();
        TextField passWord = new TextField();
        TextField email = new TextField();
        TextField phoneNumber = new TextField();
        ComboBox<String> economicClass = new ComboBox<>();

        // Pass the real components and the mocked DAO to the mock controller
        mockController = new MockUserRegistrationController(
                firstName, lastName, userName, passWord, email, phoneNumber, economicClass, mockUserDAO
        );
    }

    @Test
    public void testHandleUserButtonActionWithEmptyFields() {
        // Simulate empty fields by setting all fields to empty strings
        mockController.handleUserButtonAction();

        // Verify that the mock alert message is set correctly
        assertEquals("Mock alert: Fields are empty.", mockController.getMockAlertMessage());
    }

    @Test
    public void testHandleUserButtonActionWithValidFields() {
        // Set valid values in the fields
        mockController.firstName.setText("ValidFirstName");
        mockController.lastName.setText("ValidLastName");
        mockController.userName.setText("ValidUser");
        mockController.passWord.setText("ValidPass");
        mockController.email.setText("validEmail@test.com");
        mockController.phoneNumber.setText("1234567890");
        mockController.economicClass.setValue("Middle");

        // Simulate a button click with valid fields
        mockController.handleUserButtonAction();

        // Verify that the correct message is set for valid fields
        assertEquals("Fields are valid.", mockController.getMockAlertMessage());
    }

    @Test
    public void testPhoneNumberIsInvalid() {
        // Set valid values for all fields except for the phone number
        mockController.firstName.setText("ValidFirstName");
        mockController.lastName.setText("ValidLastName");
        mockController.userName.setText("ValidUser");
        mockController.passWord.setText("ValidPass");
        mockController.email.setText("validEmail@test.com");
        mockController.phoneNumber.setText("invalidPhoneNumber"); // Invalid phone number
        mockController.economicClass.setValue("Middle");

        // Simulate a button click
        mockController.handleUserButtonAction();

        // Verify that the phone number validation fails
        assertEquals("Mock alert: Invalid phone number.", mockController.getMockAlertMessage());
    }


    @Test
    public void testUsernameIsUnique() {
        // Mock behavior to return true, meaning the username exists in the database
        Mockito.when(mockUserDAO.CheckUsername("existingUser")).thenReturn(true);

        // Validate that username uniqueness check works (username is NOT unique)
        assertFalse(mockController.isUsernameUnique("existingUser"));

        // Mock behavior to return false, meaning the username does not exist in the database
        Mockito.when(mockUserDAO.CheckUsername("newUser")).thenReturn(false);

        // Validate that username uniqueness check works (username is unique)
        assertTrue(mockController.isUsernameUnique("newUser"));
    }

    @Test
    public void testEmailIsUnique() {
        // Mock behavior to return true, meaning the email exists in the database
        Mockito.when(mockUserDAO.checkEmail("existingemail@example.com")).thenReturn(true);

        // Validate that email uniqueness check works (email is NOT unique)
        assertFalse(mockController.isEmailUnique("existingemail@example.com"));

        // Mock behavior to return false, meaning the email does not exist in the database
        Mockito.when(mockUserDAO.checkEmail("newemail@example.com")).thenReturn(false);

        // Validate that email uniqueness check works (email is unique)
        assertTrue(mockController.isEmailUnique("newemail@example.com"));
    }
}
