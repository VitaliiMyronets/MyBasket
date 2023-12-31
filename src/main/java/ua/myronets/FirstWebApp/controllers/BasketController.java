package ua.myronets.FirstWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.myronets.FirstWebApp.models.Basket;
import ua.myronets.FirstWebApp.repo.BasketRepository;

import java.util.ArrayList;
import java.util.Optional;

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
    public String myBasketPostAdd(@RequestParam String basketName,@RequestParam String description,@RequestParam String creator) {
        Basket basket = new Basket(basketName, description, creator);
        basketRepository.save(basket);
        return "redirect:/mybaskets";

    }
    @GetMapping("/mybaskets/{id}")
    public String myBasketsDetails(@PathVariable(value = "id") long id, Model model){
        if(!basketRepository.existsById(id)){
            return "redirect:/mybaskets";
        }

        Optional<Basket> baskets = basketRepository.findById(id);
        ArrayList<Basket> res = new ArrayList<>();
        baskets.ifPresent(res::add);
        model.addAttribute("baskets", res);
        return "myBasketsDetails";
    }

    @GetMapping("/mybaskets/{id}/edit")
    public String basketEdit(@PathVariable(value = "id") long id, Model model){
        if(!basketRepository.existsById(id)){
            return "redirect:/mybaskets";
        }
        Optional<Basket> basket = basketRepository.findById(id);
        ArrayList<Basket> res = new ArrayList<>();
        basket.ifPresent(res::add);
        model.addAttribute("basket", res);
        return "myBasketsEdit";
    }
    @PostMapping("/mybaskets/{id}/edit")
    public String myBasketUpdate(@PathVariable(value = "id") long id, @RequestParam String basketName,@RequestParam String description,@RequestParam String creator, Model model){
        Basket basket = basketRepository.findById(id).orElseThrow();
        basket.setBasketName(basketName);
        basket.setDescription(description);
        basket.setCreator(creator);
        basketRepository.save(basket);

        return "redirect:/mybaskets";
    }

    @PostMapping("/mybaskets/{id}/remove")
    public String myBasketDelete(@PathVariable(value = "id") long id, Model model){
        Basket basket = basketRepository.findById(id).orElseThrow();
        basketRepository.delete(basket);

        return "redirect:/mybaskets";
    }

}

