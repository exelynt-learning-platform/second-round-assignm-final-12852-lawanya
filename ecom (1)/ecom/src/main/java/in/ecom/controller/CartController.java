package in.ecom.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.ecom.model.Cart;
import in.ecom.model.CartItem;
import in.ecom.model.Product;
import in.ecom.model.User;
import in.ecom.repository.CartRepository;
import in.ecom.repository.ProductRepository;
import in.ecom.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	@Autowired
    private CartRepository cartRepo;

    @Autowired
    private ProductRepository productRepo;

    // 🟢 Get Cart
    @GetMapping
    public Cart getCart(Authentication auth) {

        User user = (User) auth.getPrincipal();

        return cartRepo.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }
    

  
    @PostMapping("/add")
    public Cart addToCart(Authentication auth,
                          @RequestParam Long productId,
                          @RequestParam int qty) {

        User user = (User) auth.getPrincipal();

        Cart cart = cartService.getOrCreateCart(user);
        cart.setUser(user);

        Product product = productRepo.findById(productId).orElseThrow();

        CartItem item = new CartItem();
        item.setProduct(product);
        item.setQuantity(qty);

        cart.getItems().add(item);

        return cartRepo.save(cart);
    }


    @DeleteMapping("/remove/{itemId}")
    public Cart removeItem(@PathVariable Long itemId,
                           Authentication auth) {

        User user = (User) auth.getPrincipal();

        Cart cart = cartRepo.findByUser(user).orElseThrow();

        cart.getItems().removeIf(i -> i.getId().equals(itemId));

        return cartRepo.save(cart);
    }

    
    @DeleteMapping("/clear")
    public String clearCart(Authentication auth) {

        User user = (User) auth.getPrincipal();

        Cart cart = cartRepo.findByUser(user).orElseThrow();

        cart.getItems().clear();
        cartRepo.save(cart);

        return "Cart cleared";
    }
    
}
