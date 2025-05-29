package ua.myronets.FirstWebApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.myronets.FirstWebApp.models.User;
import ua.myronets.FirstWebApp.service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAllUsers()); // Викликаємо findAllUsers з UserService
        model.addAttribute("title", "Список користувачів");
        return "user/users"; // Створіть цей шаблон
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") long id, Model model){
        Optional<User> userOptional = userService.findUserById(id);
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
            model.addAttribute("title", "Деталі користувача: " + userOptional.get().getUsername());
            return "user/user"; // Створіть цей шаблон
        }
        return "redirect:/users"; // Якщо користувача не знайдено, перенаправляємо на список
    }

    // Метод для відображення форми редагування користувача (якщо потрібно)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/{id}/edit")
    public String showEditUserForm(@PathVariable("id") Long id, Model model) {
        Optional<User> userOptional = userService.findUserById(id);
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
            model.addAttribute("title", "Редагувати користувача");
            return "user/edit-user"; // Створіть цей шаблон
        }
        return "redirect:/users";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute User user) { // Використовуйте User, а не UserRegistrationDto для оновлення
        Optional<User> existingUserOptional = userService.findUserById(id);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setRole(user.getRole()); // Дозволити адміністратору змінювати роль
            // НЕ МІНЯЙТЕ ПАРОЛЬ ТУТ НАПРЯМУ! Для пароля має бути окремий механізм.
            userService.updateUser(existingUser);
        }
        return "redirect:/users/{id}";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}