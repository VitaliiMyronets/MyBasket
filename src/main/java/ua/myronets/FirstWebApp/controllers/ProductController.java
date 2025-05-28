package ua.myronets.FirstWebApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.security.Principal; // Використовуємо стандартний Principal

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
                return "products";
            }
        }
        return "redirect:/home";
    }

    @PostMapping("/warehouse/{warehouseId}/products/add")
    public String addProductToWarehouse(@PathVariable Long warehouseId,
                                        @ModelAttribute Product newProduct,
                                        Principal principal) {
        Optional<Warehouse> warehouseOptional = warehouseService.findWarehouseById(warehouseId);

        if (warehouseOptional.isPresent()) {
            Warehouse warehouse = warehouseOptional.get();


            if (principal != null && warehouse.getUser().getUsername().equals(principal.getName())) {
                newProduct.setWarehouse(warehouse);
                productService.saveProduct(newProduct);
                return "redirect:/warehouse/" + warehouseId + "/products";
            }
        }
        return "redirect:/home";
    }
}
