package ua.myronets.FirstWebApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import ua.myronets.FirstWebApp.models.Product;
import ua.myronets.FirstWebApp.models.Warehouse;
import ua.myronets.FirstWebApp.service.ProductService;
import ua.myronets.FirstWebApp.service.WarehouseService;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final WarehouseService warehouseService;

    @GetMapping("/warehouse/{warehouseId}/products")
    public String showProductsInWarehouse(@PathVariable Long warehouseId, Model model,
                                          Principal principal) {
        Optional<Warehouse> warehouseOptional = warehouseService.findWarehouseById(warehouseId);

        if (warehouseOptional.isPresent()) {
            Warehouse warehouse = warehouseOptional.get();
            if (principal != null && warehouse.getUser().getUsername().equals(principal.getName())) {
                model.addAttribute("warehouse", warehouse);
                List<Product> products = productService.findProductByWarehouse(warehouse);
                model.addAttribute("products", products);
                model.addAttribute("newProduct", new Product());

                model.addAttribute("title", "Продукти на складі: " + warehouse.getName());
                return "warehouse";
            }
        }
        return "redirect:/home";
    }



    @GetMapping("/product/{id}/edit")
    public String showEditProductForm(@PathVariable Long id, Model model, Principal principal) {
        Optional<Product> productOptional = productService.findProductById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            if (principal != null && product.getWarehouse().getUser().getUsername().equals(principal.getName())) {
                model.addAttribute("product", product);
                model.addAttribute("title", "Редагувати продукт: " + product.getName());
                return "edit-product";
            }
        }
        return "redirect:/home";
    }

    @PostMapping("/product/{id}/edit")
    public String updateProduct(@PathVariable Long id,
                                @ModelAttribute Product updatedProduct,
                                Principal principal) {
        Optional<Product> existingProductOptional = productService.findProductById(id);

        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            if (principal != null && existingProduct.getWarehouse().getUser().getUsername().equals(principal.getName())) {
                existingProduct.setName(updatedProduct.getName());
                existingProduct.setVolume(updatedProduct.getVolume());
                existingProduct.setNumber(updatedProduct.getNumber());
                productService.saveProduct(existingProduct);
                return "redirect:/warehouse/" + existingProduct.getWarehouse().getId() + "/products"; // Повертаємося до списку продуктів складу
            }
        }
        return "redirect:/home";
    }

    @PostMapping("/product/{id}/delete")
    public String deleteProduct(@PathVariable Long id, Principal principal) {
        Optional<Product> productOptional = productService.findProductById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            Long warehouseId = product.getWarehouse().getId();
            if (principal != null && product.getWarehouse().getUser().getUsername().equals(principal.getName())) {
                productService.deleteProduct(id);
                return "redirect:/warehouse/" + warehouseId + "/products";
            }
        }
        return "redirect:/home";
    }

    @PostMapping("/product/{productId}/change-quantity")
    public String changeProductQuantity(
            @PathVariable Long productId,
            @RequestParam("action") String action,
            Principal principal) {

        Optional<Product> productOptional = productService.findProductById(productId);

        if (productOptional.isEmpty()) {
            // Можливо, обробити помилку або просто перенаправити на головну
            return "redirect:/home";
        }

        Product product = productOptional.get();

        // Перевірка прав доступу: чи належить продукт складу, який належить поточному користувачу
        if (principal == null || !product.getWarehouse().getUser().getUsername().equals(principal.getName())) {
            return "redirect:/home"; // Доступ заборонено
        }

        int currentNumber = product.getNumber();
        int newNumber = currentNumber;

        if ("increment".equals(action)) {
            newNumber++;
        } else if ("decrement".equals(action)) {
            newNumber--;
        }

        if (newNumber < 0) {
            newNumber = 0;
        }

        product.setNumber(newNumber);
        productService.saveProduct(product);

        return "redirect:/warehouse/" + product.getWarehouse().getId();
    }
}