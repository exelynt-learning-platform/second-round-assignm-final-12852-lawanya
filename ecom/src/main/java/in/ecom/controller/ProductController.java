package in.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ecom.model.Product;
import in.ecom.repository.ProductRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	 @Autowired
	    private ProductRepository repo;
	 

	    @GetMapping
	    public List<Product> getAll() {
	        return repo.findAll();
	    }

	    @GetMapping("/{id}")
	    public Product getOne(@PathVariable Long id) {
	        return repo.findById(id).orElseThrow();
	    }

	    @PostMapping
	    public Product create(@Valid @RequestBody Product product) {
	        return repo.save(product);
	    }

	    @PutMapping("/{id}")
	    public Product update(@PathVariable Long id,
	                          @RequestBody Product updated) {

	        Product product = repo.findById(id).orElseThrow();

	        product.setName(updated.getName());
	        product.setPrice(updated.getPrice());
	        product.setStock(updated.getStock());

	        return repo.save(product);
	    }

	    @DeleteMapping("/{id}")
	    public String delete(@PathVariable Long id) {
	        repo.deleteById(id);
	        return "Deleted successfully";
	    }
}
