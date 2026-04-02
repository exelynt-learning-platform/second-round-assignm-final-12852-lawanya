package in.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ecom.model.User;
import in.ecom.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
	 @Autowired
	    private UserRepository repo;

	    @GetMapping
	    public List<User> getAllUsers() {
	        return repo.findAll();
	    }

	    @GetMapping("/{id}")
	    public User getUser(@PathVariable Long id) {
	        return repo.findById(id).orElseThrow();
	    }
}
