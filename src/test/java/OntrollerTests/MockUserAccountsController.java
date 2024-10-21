package OntrollerTests;

import com.example.finalassignmentcab302.Controllers.UserAccountsController;
import com.example.finalassignmentcab302.Tables.Order;
import com.example.finalassignmentcab302.dao.OrderDAO;
import com.example.finalassignmentcab302.dao.OrganisationDAO;
import com.example.finalassignmentcab302.dao.UserDAO;

import java.util.ArrayList;
import java.util.List;

public class MockUserAccountsController extends UserAccountsController {

    public UserDAO mockUserDAO;
    public OrderDAO mockOrderDAO;
    public OrganisationDAO mockOrganisationDAO;

    public List<Order> mockOrders = new ArrayList<>();
    private Order selectedOrder;
    public Integer userID = 1;

    // Constructor to initialize mocked DAOs
    public MockUserAccountsController(UserDAO userDAO, OrderDAO orderDAO, OrganisationDAO organisationDAO) {
        this.mockUserDAO = userDAO;
        this.mockOrderDAO = orderDAO;
        this.mockOrganisationDAO = organisationDAO;
    }

    // Method to set orders for testing
    public void setOrdersForTesting(List<Order> orders) {
        this.mockOrders.clear();
        this.mockOrders.addAll(orders);
    }

    // Overriding syncOrders to use the mockOrders list
    @Override
    public void syncOrders() {
        List<Order> userOrders = mockOrderDAO.getUserOrders(userID);
        mockOrders.clear();
        mockOrders.addAll(userOrders);

    }

    // Overriding selectOrder to set the selected order
    @Override
    public void selectOrder(Order order) {
        super.selectOrder(order); // Call the original method to keep existing functionality
        this.selectedOrder = order; // Store the selected order
    }

}

// Getter for the selected order

