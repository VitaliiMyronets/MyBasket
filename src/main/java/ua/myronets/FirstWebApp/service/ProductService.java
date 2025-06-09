package ua.myronets.FirstWebApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.myronets.FirstWebApp.models.Product;
import ua.myronets.FirstWebApp.models.Warehouse;
import ua.myronets.FirstWebApp.repo.ProductRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findProductByWarehouse(Warehouse warehouse){
        return productRepository.findByWarehouse(warehouse);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }


    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }


    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findProductByWarehouse(Optional<Warehouse> warehouseOptional) {
        if (warehouseOptional.isPresent()) {
            Warehouse warehouse = warehouseOptional.get();
            return productRepository.findByWarehouse(warehouse);
        }
        return Collections.emptyList();
    }
}
