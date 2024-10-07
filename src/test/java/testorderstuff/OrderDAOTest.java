package testorderstuff;

import com.example.finalassignmentcab302.Tables.Order;
import com.example.finalassignmentcab302.dao.OrderDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderDAOTest {
    private OrderDAO orderDAO;
    private Order[] orders = {
            new Order(1, 1, "2023-01-01 10:00:00", 100.0f, "123 Main St"),
            new Order(2, 1, "2023-01-02 11:00:00", 200.0f, "456 Oak St"),
            new Order(1, 2, "2023-01-03 12:00:00", 300.0f, "789 Pine St")
    };

    @BeforeEach
    public void setUp() {
        orderDAO = new MockOrderDAO();
    }

    @Test
    public void testInsertAndGetAll() {
        for (Order order : orders) {
            orderDAO.insert(order);
        }
        List<Order> allOrders = orderDAO.getAll();
        assertEquals(3, allOrders.size());
    }

    @Test
    public void testUpdate() {
        orderDAO.insert(orders[0]);
        Order updatedOrder = new Order(
                1, 1, 2, "2023-01-05 14:00:00", 500.0f, "321 Birch St"
        );
        orderDAO.update(updatedOrder);
        List<Order> allOrders = orderDAO.getAll();
        Order fetchedOrder = allOrders.get(0);
        assertEquals("2023-01-05 14:00:00", fetchedOrder.getOrderDateTime());
        assertEquals(500.0f, fetchedOrder.getAmount());
        assertEquals("321 Birch St", fetchedOrder.getBillingAddress());
    }

    @Test
    public void testDelete() {
        for (Order order : orders) {
            orderDAO.insert(order);
        }
        orderDAO.delete(1);
        List<Order> allOrders = orderDAO.getAll();
        assertEquals(2, allOrders.size());
    }
}
