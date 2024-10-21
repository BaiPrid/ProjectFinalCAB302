package OntrollerTests;

import com.example.finalassignmentcab302.dao.UserAnswersDAO;
import com.example.finalassignmentcab302.dao.OrganisationDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CharitiesPageControllerTest {

    private MockCharitiesPageController mockController;
    private UserAnswersDAO mockUserAnswersDAO;
    private OrganisationDAO mockOrganisationDAO;

    @BeforeEach
    public void setUp() {
        // Mocking the DAOs
        mockUserAnswersDAO = Mockito.mock(UserAnswersDAO.class);
        mockOrganisationDAO = Mockito.mock(OrganisationDAO.class);

        // Pass the mocked DAOs to the mock controller
        mockController = new MockCharitiesPageController(mockUserAnswersDAO, mockOrganisationDAO);
    }

    @Test
    public void testInitializeWithNoUserAnswers() {
        // Simulate no user answers for the current user
        when(mockUserAnswersDAO.getUserAnswers(Mockito.anyInt())).thenReturn(null);

        // Call the initialize method
        mockController.initialize();

        // Verify that the mock label message is set correctly
        assertEquals("No user answers found!", mockController.getLblCharity1Text());
    }

    @Test
    public void testInitializeWithValidUserAnswers() {
        // Simulate valid user answers and matching organizations
        when(mockUserAnswersDAO.getUserAnswers(Mockito.anyInt())).thenReturn(List.of("Answer1", "Answer2"));
        when(mockUserAnswersDAO.getMatchingOrganisations(Mockito.anyList())).thenReturn(Map.of(1, 3, 2, 2, 3, 1));
        when(mockOrganisationDAO.getName(1)).thenReturn("Charity 1");
        when(mockOrganisationDAO.getDescription(1)).thenReturn("Description 1");
        when(mockOrganisationDAO.getName(2)).thenReturn("Charity 2");
        when(mockOrganisationDAO.getDescription(2)).thenReturn("Description 2");
        when(mockOrganisationDAO.getName(3)).thenReturn("Charity 3");
        when(mockOrganisationDAO.getDescription(3)).thenReturn("Description 3");

        // Call the initialize method
        mockController.initialize();

        // Verify that the charities are populated correctly
        assertEquals("Charity 1", mockController.getLblCharity1Text());
        assertEquals("Description 1\nPercentage match: 60.00%", mockController.getTxtCharity1Text());
        assertEquals("Charity 2", mockController.getLblCharity2Text());
        assertEquals("Description 2\nPercentage match: 40.00%", mockController.getTxtCharity2Text());
        assertEquals("Charity 3", mockController.getLblCharity3Text());
        assertEquals("Description 3\nPercentage match: 20.00%", mockController.getTxtCharity3Text());
    }
}