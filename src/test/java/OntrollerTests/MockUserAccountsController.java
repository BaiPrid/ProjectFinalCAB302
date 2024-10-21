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
    public Order selectedOrder;
    public Integer userID = 1;
    public String userName;
    public String txt;

    // Constructor to initialize mocked DAOs
    public MockUserAccountsController(UserDAO userDAO, OrderDAO orderDAO, OrganisationDAO organisationDAO) {
        this.mockUserDAO = userDAO;
        this.mockOrderDAO = orderDAO;
        this.mockOrganisationDAO = organisationDAO;
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
        selectedOrder = order; // Store the selected order
    }

    @Override
    public void initialize() {
        txt = "Welcome " + userName + "!";
    }

}

// Getter for the selected order

