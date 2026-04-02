package in.ecom.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.ecom.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	 @Autowired
	    private PaymentService service;

	    @PostMapping("/create-intent")
	    public Map<String, String> pay(Authentication auth,
	                                   @RequestParam Double amount) {

	        try {
	            var intent = service.createPaymentIntent(amount);
	            return Map.of("clientSecret", intent.getClientSecret());
	        } catch (Exception e) {
	            throw new RuntimeException("Payment failed: " + e.getMessage());
	        }
	    }
}
