package in.ecom.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stripe.model.PaymentIntent;
import in.ecom.model.Cart;
import in.ecom.model.Order;
import in.ecom.model.OrderItem;
import in.ecom.model.User;
import in.ecom.repository.CartRepository;
import in.ecom.repository.OrderRepository;
import in.ecom.service.CartService;
import in.ecom.service.PaymentService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	 @Autowired
	    private OrderRepository orderRepo;

	    @Autowired
	    private CartService cartService; // ✅ use service instead of repo

	    @Autowired
	    private PaymentService paymentService;

	    // ✅ Create Order
	    @PostMapping
	    public Map<String, Object> createOrder(Authentication auth) {

	        User user = (User) auth.getPrincipal();

	        // ✅ get cart properly
	        Cart cart = cartService.getOrCreateCart(user);

	        if (cart.getItems().isEmpty()) {
	            throw new RuntimeException("Cart is empty");
	        }

	        Order order = new Order();
	        order.setUser(user);
	        order.setStatus("PENDING");

	        // ✅ create order items
	        List<OrderItem> items = cart.getItems().stream().map(c -> {
	            OrderItem o = new OrderItem();
	            o.setProduct(c.getProduct());
	            o.setQuantity(c.getQuantity());
	            o.setOrder(order); // 🔥 important
	            return o;
	        }).toList();

	        // ✅ calculate total
	        double total = items.stream()
	                .mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity())
	                .sum();

	        order.setItems(items);
	        order.setTotalAmount(total);

	        // ✅ save order
	        orderRepo.save(order);

	        // ✅ clear cart after order
	        cart.getItems().clear();
	        cartService.save(cart);

	        // ✅ Stripe Payment
	        PaymentIntent intent = paymentService.createPaymentIntent(total);

	        return Map.of(
	                "orderId", order.getId(),
	                "amount", total,
	                "clientSecret", intent.getClientSecret()
	        );
	    }

	    // ✅ Get My Orders (OPTIMIZED)
	    @GetMapping
	    public List<Order> getMyOrders(Authentication auth) {

	        User user = (User) auth.getPrincipal();

	        return orderRepo.findByUser(user); // 🔥 better than filtering
	    }

	    // ✅ Get Order by ID
	    @GetMapping("/{id}")
	    public Order getOrder(@PathVariable Long id, Authentication auth) {

	        User user = (User) auth.getPrincipal();

	        Order order = orderRepo.findById(id)
	                .orElseThrow(() -> new RuntimeException("Order not found"));

	        if (!order.getUser().getId().equals(user.getId())) {
	            throw new RuntimeException("Unauthorized access");
	        }

	        return order;
	    }
}
