package testingtablesfolder;

import com.example.finalassignmentcab302.Tables.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Ordertest {
    private Order order;

    @BeforeEach
    public void setUp() {
        // Initialize the Order object before each test
        order = new Order(1, 100, 200, "2024-09-15T10:00:00", 99.99f, "123 Example St");
    }

    @Test
    public void testGetOrderId() {
        assertEquals(1, order.getOrderId());
    }

    @Test
    public void testGetUserId() {
        assertEquals(100, order.getUserId());
    }

    @Test
    public void testGetOrganisationId() {
        assertEquals(200, order.getOrganisationId());
    }

    @Test
    public void testGetOrderDateTime() {
        assertEquals("2024-09-15T10:00:00", order.getOrderDateTime());
    }

    @Test
    public void testGetAmount() {
        assertEquals(99.99f, order.getAmount());
    }

    @Test
    public void testGetBillingAddress() {
        assertEquals("123 Example St", order.getBillingAddress());
    }

}
