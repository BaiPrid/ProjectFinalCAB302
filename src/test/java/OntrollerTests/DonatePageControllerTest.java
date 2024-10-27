package OntrollerTests;
import com.example.finalassignmentcab302.dao.OrderDAO;
import com.example.finalassignmentcab302.dao.OrganisationAnswersDAO;
import com.example.finalassignmentcab302.dao.OrganisationDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DonatePageControllerTest {

    private OntrollerTests.MockDonatePageController mockController;
    private OrganisationDAO mockOrganisationDAO;
    private OrganisationAnswersDAO mockOrganisationAnswersDAO;
    private OrderDAO mockOrderDAO;

    @BeforeEach
    public void setUp() {
        // Mocking the DAOs
        mockOrganisationDAO = Mockito.mock(OrganisationDAO.class);
        mockOrganisationAnswersDAO = Mockito.mock(OrganisationAnswersDAO.class);
        mockOrderDAO = Mockito.mock(OrderDAO.class);

        // Pass the mocked DAOs to the mock controller
        mockController = new OntrollerTests.MockDonatePageController(mockOrganisationDAO, mockOrganisationAnswersDAO, mockOrderDAO);
    }

    @Test
    public void testOnDonatePressWithEmptyFields() {
        // Simulate empty billing address and no donation selected
        mockController.setFieldsForTesting("", null);

        // Simulate the button action
        mockController.OnDonatePress();

        // Verify that the mock alert message is set correctly
        assertEquals("Mock alert: Form incomplete. Please fill in all required fields.", mockController.getMockAlertMessage());
    }

    @Test
    public void testOnDonatePressWithValidFields() {
        // Set valid values in the fields
        mockController.setFieldsForTesting("123 Fake St", 50);

        // Simulate the button action
        mockController.OnDonatePress();

        // Verify that the correct message is set for valid fields
        assertEquals("Donation successful.", mockController.getMockAlertMessage());

        // Verify that the orderDAO's insert method was called
        verify(mockOrderDAO).insert(Mockito.any());
    }

    @Test
    public void testSetCharityInfo() {
        // Mock the data returned by the DAO
        when(mockOrganisationDAO.getByName("Test Charity")).thenReturn(List.of(1, "Charity Name", "Description", "imgPath", "email", "Group Supported"));
        when(mockOrganisationAnswersDAO.getByid(1)).thenReturn(List.of(1, "Category", "Size", "DonationOptions", "Taxable Category"));


        mockController.setCharityInfo("Test Charity");

        // Verify the fields are set correctly
        assertEquals("Charity Name", mockController.getMockLblCharityName());
        assertEquals("Description", mockController.getMockCharityDescription());
        assertEquals("Group Supported", mockController.getMockCharityGroupSupported());
        assertEquals("Taxable Category", mockController.getMockTaxableCategory());
        assertEquals("Size", mockController.getMockCharitySize());
    }
}