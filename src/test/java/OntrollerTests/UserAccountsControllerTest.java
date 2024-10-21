package OntrollerTests;

import com.example.finalassignmentcab302.Controllers.UserAccountsController;
import com.example.finalassignmentcab302.dao.OrderDAO;
import com.example.finalassignmentcab302.dao.OrganisationDAO;
import com.example.finalassignmentcab302.dao.UserDAO;
import com.example.finalassignmentcab302.Tables.Order;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserAccountsControllerTest {

    private MockUserAccountsController mockController;
    private UserDAO mockUserDAO;
    private OrderDAO mockOrderDAO;
    private OrganisationDAO mockOrganisationDAO;

    @BeforeEach
    public void setUp() {
        // Mocking the DAOs
        mockUserDAO = Mockito.mock(UserDAO.class);
        mockOrderDAO = Mockito.mock(OrderDAO.class);
        mockOrganisationDAO = Mockito.mock(OrganisationDAO.class);

        // Pass the mocked DAOs to the mock controller
        mockController = new MockUserAccountsController(mockUserDAO, mockOrderDAO, mockOrganisationDAO);
    }

    @Test
    public void testSyncOrdersWithNoOrders() {
        // Mock the DAO to return an empty list
        when(mockOrderDAO.getUserOrders(anyInt())).thenReturn(List.of());

        // Sync orders
        mockController.syncOrders();

        // Verify that the ListView is empty
        assertTrue(mockController.mockOrders.isEmpty());
    }

    @Test
    public void testSyncOrdersWithOrders() {
        // Mock the DAO to return a list with one order
        Order order = new Order(1, 1, "2024-10-20T10:00:00", 50.0f, "123 Fake St");
        when(mockOrderDAO.getUserOrders(1)).thenReturn(List.of(order));

        // Sync orders
        mockController.syncOrders();

        // Verify that the ListView contains the order
        assertEquals(1, mockController.mockOrders.size());
        assertEquals(order, mockController.mockOrders.get(0));
    }

    @Test
    public void testSelectOrderUpdatesFields() {
        // Mock the organisation name returned by the DAO
        when(mockOrganisationDAO.getName(anyInt())).thenReturn("Charity Name");

        // Create a sample order
        Order order = new Order(1, 1, "2024-10-20T10:00:00", 50.0f, "123 Fake St");

        // Select the order
        mockController.selectOrder(order);

        // Verify that the fields are updated correctly
        assertEquals(1, mockController.selectedOrder.getUserId());
        assertEquals("2024-10-20T10:00:00", mockController.selectedOrder.getOrderDateTime());
        assertEquals(50.0f, mockController.selectedOrder.getAmount());
    }

    @Test
    public void testInitializeSetsTitle() {
        // Mock the user's name as if it were returned by the DAO
        mockController.userName = "John Doe";

        // Initialize the controller
        mockController.initialize();

        // Verify that the title is set correctly
        assertEquals("Welcome John Doe!", mockController.txt);
    }
}
