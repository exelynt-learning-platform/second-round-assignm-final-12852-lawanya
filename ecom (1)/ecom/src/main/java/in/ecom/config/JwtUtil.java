package in.ecom.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

   
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .signWith(SignatureAlgorithm.HS256, secret.getBytes()) // 🔥 FIX
                .compact();
    }


    public String extractEmail(String token) {
        return Jwts.parser()
                .setSigningKey(secret.getBytes()) // 🔥 FIX
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // ✅ ADD THIS (VERY IMPORTANT)
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(secret.getBytes()) // 🔥 FIX
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}


