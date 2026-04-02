package in.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ecom.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
