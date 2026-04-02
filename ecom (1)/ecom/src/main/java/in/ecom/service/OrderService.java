package in.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import in.ecom.model.Order;
import in.ecom.model.OrderItem;
import in.ecom.model.User;
import in.ecom.repository.OrderRepository;

@Service
public class OrderService {
	 @Autowired
	    private OrderRepository orderRepo;

	    public Order create(User user, List<OrderItem> items) {

	        Order order = new Order();
	        order.setUser(user);
	        order.setItems(items);

	        double total = items.stream()
	                .mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity())
	                .sum();

	        order.setTotalAmount(total);
	        order.setStatus("CREATED");

	        return orderRepo.save(order);
	    }
}
