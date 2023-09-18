package ua.myronets.FirstWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/mybaskets/add")
    public String myBasketsAdd(Model model){
        return "myBasketsAdd";
    }

    @PostMapping("/mybaskets/add")
    public String myBasketPostAdd(@RequestParam String basketName,@RequestParam String description,@RequestParam String creator){
        Basket basket = new Basket(basketName, description, creator);
        basketRepository.save(basket);
        return "redirect:/mybaskets";
    }
}

