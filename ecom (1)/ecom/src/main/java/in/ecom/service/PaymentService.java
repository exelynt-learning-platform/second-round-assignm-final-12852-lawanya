package in.ecom.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
@Service
public class PaymentService {
	 @Value("${stripe.secret}")
	    private String stripeKey;

	    public PaymentIntent createPaymentIntent(Double amount) {
	        try {
	            // ✅ VERY IMPORTANT
	            Stripe.apiKey = stripeKey;

	            Map<String, Object> params = new HashMap<>();
	            params.put("amount", (long) (amount * 100));
	            params.put("currency", "inr");

	            return PaymentIntent.create(params);

	        } catch (Exception e) {
	            throw new RuntimeException("Stripe error: " + e.getMessage());
	        }
	    }
}
