package ua.myronets.FirstWebApp.repo;

import org.springframework.data.repository.CrudRepository;
import ua.myronets.FirstWebApp.models.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
