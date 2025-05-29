package ua.myronets.FirstWebApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.myronets.FirstWebApp.models.User;
import ua.myronets.FirstWebApp.models.Warehouse;
import ua.myronets.FirstWebApp.service.UserService;
import ua.myronets.FirstWebApp.service.WarehouseService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    private final WarehouseService warehouseService;

    @GetMapping("/home")
    public String homePage(Model model, Principal principal) {
        model.addAttribute("title", "Головна");

        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("username", username);

            Optional<User> currentUser = userService.findByUsername(username);
            if (currentUser.isPresent()) {
                List<Warehouse> userWarehouses = warehouseService.getWarehousesByUser(currentUser.get());
                model.addAttribute("warehouses", userWarehouses);
                model.addAttribute("newWarehouse", new Warehouse());
            } else {
                model.addAttribute("warehouses", List.of());
                model.addAttribute("newWarehouse", new Warehouse());
            }
        } else {
            model.addAttribute("warehouses", List.of());
            model.addAttribute("newWarehouse", new Warehouse());
        }
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model, Principal principal) {
        model.addAttribute("title", "Про нас");
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "about";
    }

    @GetMapping("/myprofile")
    public String myProfile(Model model, Principal principal) {
        model.addAttribute("title", "Мій профіль");
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "myprofile";
    }
}