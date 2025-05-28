package ua.myronets.FirstWebApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.security.Principal;

import ua.myronets.FirstWebApp.models.User;
import ua.myronets.FirstWebApp.models.Warehouse;
import ua.myronets.FirstWebApp.service.UserService;
import ua.myronets.FirstWebApp.service.WarehouseService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;
    private final UserService userService;



    @PostMapping("/warehouse/add")
    public String addWarehouse(@ModelAttribute Warehouse newWarehouse,
                               Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            Optional<User> currentUser = userService.findByUsername(username);
            if (currentUser.isPresent()) {
                newWarehouse.setUser(currentUser.get());
                warehouseService.saveWarehouse(newWarehouse);
            }
        }
        return "redirect:/home";
    }

    @GetMapping("/warehouse/{id}")
    public String showWarehouseDetails(@PathVariable Long id, Model model,
                                       Principal principal) {
        Optional<Warehouse> warehouse = warehouseService.findWarehouseById(id);

        if (warehouse.isPresent()) {
            if (principal != null && warehouse.get().getUser().getUsername().equals(principal.getName())) {
                model.addAttribute("warehouse", warehouse.get());
                return "warehouse-details";
            }
        }
        return "redirect:/home";
    }
}