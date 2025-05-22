package ua.myronets.FirstWebApp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "Головна");
        return "home";
    }
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "Про нас");
        return "about";
    }

    @GetMapping("/myprofile")
    public String myProfile(Model model) {
        model.addAttribute("title", "Мій профіль");
        return "myprofile";
    }

}