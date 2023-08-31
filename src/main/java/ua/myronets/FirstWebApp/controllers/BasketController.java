package ua.myronets.FirstWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.myronets.FirstWebApp.models.Basket;
import ua.myronets.FirstWebApp.repo.BasketRepository;

@Controller
public class BasketController {
    @Autowired
    private BasketRepository basketRepository;

    @GetMapping("/mybaskets")
    public String myBaskets(Model model){
        Iterable<Basket> baskets = basketRepository.findAll();
        model.addAttribute("baskets", baskets);
        return "myBaskets";
    }
}
