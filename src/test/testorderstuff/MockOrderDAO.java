package testorderstuff;

import com.example.finalassignmentcab302.Tables.Order;
import com.example.finalassignmentcab302.dao.OrderDAO;

import java.util.ArrayList;
import java.util.List;

public class MockOrderDAO extends OrderDAO {
    private final List<Order> orderList = new ArrayList<>();
    private int autoIncrementedId = 1;

    @Override
    public void insert(Order order) {
        order = new Order(
                autoIncrementedId++,
                order.getUserId(),
                order.getOrganisationId(),
                order.getOrderDateTime(),
                order.getAmount(),
                order.getBillingAddress()
        );
        orderList.add(order);
    }

    @Override
    public void update(Order order) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderId() == order.getOrderId()) {
                orderList.set(i, order);
                break;
            }
        }
    }

    @Override
    public void delete(int orderId) {
        orderList.removeIf(order -> order.getOrderId() == orderId);
    }

    @Override
    public List<Order> getAll() {
        return new ArrayList<>(orderList);
    }
}
