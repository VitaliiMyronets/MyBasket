package ua.myronets.FirstWebApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.myronets.FirstWebApp.models.Product;
import ua.myronets.FirstWebApp.models.Warehouse;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByWarehouse(Warehouse warehouse);
}
