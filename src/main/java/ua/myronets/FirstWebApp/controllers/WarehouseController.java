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
                model.addAttribute("title", "Деталі складу: " + warehouse.get().getName());
                return "warehouse";
            }
        }
        return "redirect:/home";
    }


    @GetMapping("/warehouse/{id}/edit")
    public String showEditWarehouseForm(@PathVariable Long id, Model model, Principal principal) {
        Optional<Warehouse> warehouseOptional = warehouseService.findWarehouseById(id);

        if (warehouseOptional.isPresent()) {
            Warehouse warehouse = warehouseOptional.get();
            if (principal != null && warehouse.getUser().getUsername().equals(principal.getName())) {
                model.addAttribute("warehouse", warehouse);
                model.addAttribute("title", "Редагувати склад: " + warehouse.getName());
                return "edit-warehouse";
            }
        }
        return "redirect:/home";
    }

    @PostMapping("/warehouse/{id}/edit")
    public String updateWarehouse(@PathVariable Long id,
                                  @ModelAttribute Warehouse updatedWarehouse,
                                  Principal principal) {
        Optional<Warehouse> existingWarehouseOptional = warehouseService.findWarehouseById(id);

        if (existingWarehouseOptional.isPresent()) {
            Warehouse existingWarehouse = existingWarehouseOptional.get();
            if (principal != null && existingWarehouse.getUser().getUsername().equals(principal.getName())) {
                existingWarehouse.setName(updatedWarehouse.getName());
                existingWarehouse.setDescription(updatedWarehouse.getDescription());
                warehouseService.saveWarehouse(existingWarehouse);
                return "redirect:/warehouse/" + id;
            }
        }
        return "redirect:/home";
    }


    @PostMapping("/warehouse/{id}/delete")
    public String deleteWarehouse(@PathVariable Long id, Principal principal) {
        Optional<Warehouse> warehouseOptional = warehouseService.findWarehouseById(id);

        if (warehouseOptional.isPresent()) {
            Warehouse warehouse = warehouseOptional.get();
            if (principal != null && warehouse.getUser().getUsername().equals(principal.getName())) {
                warehouseService.deleteWarehouse(id);
            }
        }
        return "redirect:/home";
    }
}