package in.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ecom.model.Order;
import in.ecom.model.User;

public interface OrderRepository extends JpaRepository<Order, Long> {List<Order> findByUser(User user);
}
