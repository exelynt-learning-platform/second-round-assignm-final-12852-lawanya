package in.ecom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecom.model.Cart;


import in.ecom.model.User;
import in.ecom.repository.CartRepository;


@Service
public class CartService {
	 @Autowired
	    private CartRepository cartRepo;
	 public Cart save(Cart cart) {
		    return cartRepo.save(cart);
		}

	    public Cart getOrCreateCart(User user) {

	        return cartRepo.findByUser(user)
	                .orElseGet(() -> {
	                    Cart newCart = new Cart();
	                    newCart.setUser(user);
	                    return cartRepo.save(newCart); // ✅ saved only once
	                });
	    }
}
