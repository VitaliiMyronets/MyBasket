package ua.myronets.FirstWebApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String homePage(Model model, Principal principal) {
        model.addAttribute("title", "Головна");
        if (principal != null) {
            model.addAttribute("username", principal.getName());
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