package in.ecom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.ecom.config.JwtUtil;
import in.ecom.model.User;
import in.ecom.repository.UserRepository;

@Service
public class AuthService {
	@Autowired
    UserRepository repo;

    @Autowired
    JwtUtil jwt;

    PasswordEncoder encoder = new BCryptPasswordEncoder();

    public String register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return "Registered";
    }

    public String login(String email, String password) {
        User user = repo.findByEmail(email).orElseThrow();

        if (!encoder.matches(password, user.getPassword()))
            throw new RuntimeException("Invalid credentials");

        return jwt.generateToken(email);
    }

}
