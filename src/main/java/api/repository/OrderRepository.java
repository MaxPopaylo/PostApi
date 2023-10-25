package api.repository;

import api.entity.Order;
import api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findOrdersBySender(User sender);
    List<Order> findOrdersByRecipient(User recipient);
}
