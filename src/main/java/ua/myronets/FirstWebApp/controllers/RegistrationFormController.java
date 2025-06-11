package ua.myronets.FirstWebApp.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.myronets.FirstWebApp.dto.UserRegistrationDto;
import ua.myronets.FirstWebApp.models.User;
import ua.myronets.FirstWebApp.service.UserService;

@Controller
@RequiredArgsConstructor
public class RegistrationFormController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final HttpServletRequest request;



    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
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
            User registeredUser = userService.registerUser(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    registeredUser.getUsername(),
                    user.getPassword()
            );
            Authentication authenticatedUser = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authenticatedUser);

            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
            return "redirect:/home";

        } catch (IllegalArgumentException e) {
            model.addAttribute("registrationError", e.getMessage());
            return "/registration";
        }
    }
}
