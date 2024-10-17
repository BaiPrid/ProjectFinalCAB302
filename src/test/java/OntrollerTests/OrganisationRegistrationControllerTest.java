package OntrollerTests;

import com.example.finalassignmentcab302.dao.OrganisationDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class OrganisationRegistrationControllerTest {

    private MockOrganisationRegistrationController mockController;
    private OrganisationDAO mockOrganisationDAO;

    @BeforeEach
    public void setUp() {
        // Mocking the OrganisationDAO
        mockOrganisationDAO = Mockito.mock(OrganisationDAO.class);

        // Pass the mocked DAO to the mock controller
        mockController = new MockOrganisationRegistrationController(mockOrganisationDAO);
    }

    @Test
    public void testHandleOrganisationButtonActionWithEmptyFields() {
        // Simulate empty fields
        mockController.setFieldsForTesting("", "", "", "", "", "");

        // Simulate the button action
        mockController.handleOrganisationButtonAction();

        // Verify that the mock alert message is set correctly
        assertEquals("Mock alert: Fields are empty.", mockController.getMockAlertMessage());
    }

    @Test
    public void testHandleOrganisationButtonActionWithValidFields() {
        // Set valid values in the fields
        mockController.setFieldsForTesting("ValidOrg", "ValidGroup", "ValidDescription", "ValidUser", "ValidPass", "validEmail@test.com");

        // Simulate the button action
        mockController.handleOrganisationButtonAction();

        // Verify that the correct message is set for valid fields
        assertEquals("Fields are valid.", mockController.getMockAlertMessage());
    }

    @Test
    public void testUsernameIsUnique() {
        // Mock behavior to return true, meaning the username exists in the database
        when(mockOrganisationDAO.CheckOrganisationUserName("existingUser")).thenReturn(true);

        // Validate that username uniqueness check works (username is NOT unique)
        assertFalse(mockController.isUsernameUnique("existingUser"));

        // Mock behavior to return false, meaning the username does not exist in the database
        when(mockOrganisationDAO.CheckOrganisationUserName("newUser")).thenReturn(false);

        // Validate that username uniqueness check works (username is unique)
        assertTrue(mockController.isUsernameUnique("newUser"));
    }

    @Test
    public void testEmailIsUnique() {
        // Mock behavior to return true, meaning the email exists in the database
        when(mockOrganisationDAO.CheckOrganisationEmail("existingemail@example.com")).thenReturn(true);

        // Validate that email uniqueness check works (email is NOT unique)
        assertFalse(mockController.isEmailUnique("existingemail@example.com"));

        // Mock behavior to return false, meaning the email does not exist in the database
        when(mockOrganisationDAO.CheckOrganisationEmail("newemail@example.com")).thenReturn(false);

        // Validate that email uniqueness check works (email is unique)
        assertTrue(mockController.isEmailUnique("newemail@example.com"));
    }

    @Test
    public void testOrganisationNameIsUnique() {
        // Mock behavior to return true, meaning the organisation name exists in the database
        when(mockOrganisationDAO.CheckOrganisationName("ExistingOrg")).thenReturn(true);

        // Validate that organisation name uniqueness check works (name is NOT unique)
        assertFalse(mockController.isOrganisationNameUnique("ExistingOrg"));

        // Mock behavior to return false, meaning the organisation name does not exist in the database
        when(mockOrganisationDAO.CheckOrganisationName("NewOrg")).thenReturn(false);

        // Validate that organisation name uniqueness check works (name is unique)
        assertTrue(mockController.isOrganisationNameUnique("NewOrg"));
    }
}
