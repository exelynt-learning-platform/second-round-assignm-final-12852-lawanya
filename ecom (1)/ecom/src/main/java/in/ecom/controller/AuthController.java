package in.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ecom.model.User;
import in.ecom.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired AuthService service;

    @PostMapping("/register")
    public String register(@RequestBody User u) {
        return service.register(u);
    }

    @PostMapping("/login")
    public String login(@RequestBody User u) {
        return service.login(u.getEmail(), u.getPassword());
    }
}
