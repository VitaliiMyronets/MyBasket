package ua.myronets.FirstWebApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.myronets.FirstWebApp.dto.UserRegistrationDto;
import ua.myronets.FirstWebApp.service.UserService;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping
    public String findAll(Model model) {
//        model.addAttribute("users", userService.findAll());
//        Page<UserReadDto> page = userService.findAll(filter,pageable);
//        model.addAttribute("users", PageResponse.of(page));
//        model.addAttribute("filter", filter);
        return "user/users";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") long id){
        return "user/user";

    }



    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute UserRegistrationDto user) {
//         userService.updateUser(id, user);
         return "redirect:/users/{id}";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }


}
