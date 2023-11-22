package ua.myronets.FirstWebApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.myronets.FirstWebApp.repo.ProductRepository;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    
}
