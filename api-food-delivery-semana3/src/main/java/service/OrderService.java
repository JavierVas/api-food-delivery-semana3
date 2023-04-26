package service;

import modelo.Food;
import modelo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order createOrder(String customerName, String customerEmail, List<Food> items) {
        Order order = new Order();
        order.setCustomerName(customerName);
        order.setCustomerEmail(customerEmail);
        order.setItems(items);
        order.setCreationTime(LocalDateTime.now());
        order.setStatus("pending");
        order.setEstimatedDeliveryTime(calculateEstimatedDeliveryTime());
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setCustomerName(updatedOrder.getCustomerName());
            order.setCustomerEmail(updatedOrder.getCustomerEmail());
            order.setItems(updatedOrder.getItems());
            order.setStatus(updatedOrder.getStatus());
            order.setEstimatedDeliveryTime(updatedOrder.getEstimatedDeliveryTime());
            return orderRepository.save(order);
        } else {
            return null;
        }
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    private LocalDateTime calculateEstimatedDeliveryTime() {
        // Implement your logic to calculate the estimated delivery time here
        // For example:
        return LocalDateTime.now().plusHours(1);
    }

    public void sendConfirmationEmail(Order order) {
        // Implement your logic to send the confirmation email here
    }
}
