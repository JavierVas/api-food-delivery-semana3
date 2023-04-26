package repository;

import modelo.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(Long id);

    List<Order> findAll();

    List<Order> findByCustomerEmail(String email);

    void delete(Order order);

    void deleteById(Long id);

}
