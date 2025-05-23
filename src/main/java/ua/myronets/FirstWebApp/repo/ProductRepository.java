package ua.myronets.FirstWebApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.myronets.FirstWebApp.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
