package OntrollerTests;

import com.example.finalassignmentcab302.dao.OrganisationDAO;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class OrganisationRegistrationControllerTest {

    private MockOrganisationRegistrationController mockController;
    private OrganisationDAO mockOrganisationDAO;

    @BeforeAll
    public static void initToolkit() throws InterruptedException {
        // Use the singleton utility class to initialize JavaFX toolkit
        JavaFxInitializer.initToolkit();
    }

    @BeforeEach
    public void setUp() {
        // Mocking the OrganisationDAO
        mockOrganisationDAO = Mockito.mock(OrganisationDAO.class);

        // Use real JavaFX components instead of mocking them
        TextField organisationName = new TextField();
        TextField supportedGroup = new TextField();
        TextArea organisationDescription = new TextArea();
        TextField organisationUsername = new TextField();
        TextField organisationPassword = new TextField();
        TextField organisationEmail = new TextField();

        // Pass the real components and the mocked DAO to the mock controller
        mockController = new MockOrganisationRegistrationController(
                organisationName, supportedGroup, organisationDescription,
                organisationUsername, organisationPassword, organisationEmail,
                mockOrganisationDAO
        );
    }

    @Test
    public void testHandleOrganisationButtonActionWithEmptyFields() {
        // Simulate empty fields by setting all fields to empty strings
        mockController.handleOrganisationButtonAction();

        // Verify that the mock alert message is set correctly
        assertEquals("Mock alert: Fields are empty.", mockController.getMockAlertMessage());
    }

    @Test
    public void testHandleOrganisationButtonActionWithValidFields() {
        // Set valid values in the fields
        mockController.organisationName.setText("ValidOrg");
        mockController.supportedGroup.setText("ValidGroup");
        mockController.organisationDescription.setText("ValidDescription");
        mockController.organisationUsername.setText("validUser");
        mockController.organisationPassword.setText("validPass");
        mockController.organisationEmail.setText("validEmail@test.com");

        // Simulate a button click with valid fields
        mockController.handleOrganisationButtonAction();

        // Verify that the correct message is set for valid fields
        assertEquals("Fields are valid.", mockController.getMockAlertMessage());
    }

    @Test
    public void testEmailIsUnique() {
        // Mock behavior to return true, meaning the email exists in the database
        Mockito.when(mockOrganisationDAO.CheckOrganisationEmail("existingemail@example.com")).thenReturn(true);

        // Validate that email uniqueness check works (email is NOT unique)
        assertFalse(mockController.isEmailUnique("existingemail@example.com"));

        // Mock behavior to return false, meaning the email does not exist in the database
        Mockito.when(mockOrganisationDAO.CheckOrganisationEmail("newemail@example.com")).thenReturn(false);

        // Validate that email uniqueness check works (email is unique)
        assertTrue(mockController.isEmailUnique("newemail@example.com"));
    }

    @Test
    public void testUsernameIsUnique() {
        // Mock behavior to return true, meaning the username exists in the database
        Mockito.when(mockOrganisationDAO.CheckOrganisationUserName("existingUser")).thenReturn(true);

        // Validate that username uniqueness check works (username is NOT unique)
        assertFalse(mockController.isUsernameUnique("existingUser"));

        // Mock behavior to return false, meaning the username does not exist in the database
        Mockito.when(mockOrganisationDAO.CheckOrganisationUserName("newUser")).thenReturn(false);

        // Validate that username uniqueness check works (username is unique)
        assertTrue(mockController.isUsernameUnique("newUser"));
    }

    @Test
    public void testOrganisationNameIsUnique() {
        // Mock behavior to return true, meaning the organisation name exists in the database
        Mockito.when(mockOrganisationDAO.CheckOrganisationName("ExistingOrg")).thenReturn(true);

        // Validate that organisation name uniqueness check works (name is NOT unique)
        assertFalse(mockController.isOrganisationNameUnique("ExistingOrg"));

        // Mock behavior to return false, meaning the organisation name does not exist in the database
        Mockito.when(mockOrganisationDAO.CheckOrganisationName("NewOrg")).thenReturn(false);

        // Validate that organisation name uniqueness check works (name is unique)
        assertTrue(mockController.isOrganisationNameUnique("NewOrg"));
    }
}
