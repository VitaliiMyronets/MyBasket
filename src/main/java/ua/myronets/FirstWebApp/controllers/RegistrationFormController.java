package ua.myronets.FirstWebApp.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.myronets.FirstWebApp.dto.UserRegistrationDto;
import ua.myronets.FirstWebApp.service.UserService;

@Controller
@RequiredArgsConstructor
public class RegistrationFormController {
    private final UserService userService;



    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user",new UserRegistrationDto(
                "username",
                "firstname",
                "lastname",
                "password",
                "confirmPassword"));
        return "/registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") UserRegistrationDto user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/registration";
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("passwordMismatch", "Паролі не співпадають");
            return "/registration";
        }

        try {
            userService.registerUser(user);
            return "redirect:/home";
        } catch (IllegalArgumentException e) {
            model.addAttribute("registrationError", e.getMessage());
            return "/registration";
        }
    }
}
