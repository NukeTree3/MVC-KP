package com.nuketree3.example.mvckp.controller;

import com.nuketree3.example.mvckp.model.product.ProductService;
import com.nuketree3.example.mvckp.model.service.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class Controller{
    private final Service service;
    private final ProductService productService;

//    @GetMapping("/")
//    public String hello(Model model) throws SQLException {
//        model.addAttribute("products");
//        return "hello";
//    }


    @GetMapping("/")
    public String products(@RequestParam(value = "query", required = false) String query, Model model) {
        model.addAttribute("products", productService.searchProductByName(query));
        return "hello";
    }

    @GetMapping("/product/{id}")
    public String product(Model model, @PathVariable Long id) throws SQLException {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("count", productService.getCountProductByName(productService.getProductById(id).getName()));
        return "product-information";
    }

    @PostMapping("/product/{id}")
    public String addToCart(@PathVariable Long id, @RequestParam("quantity") int quantity) {
        System.out.println(id + " " + quantity);
        return "redirect:/product/" + id;
    }

//    @GetMapping("/{name}")
//    public String imagesForProductInformation(Model model, @PathVariable String name) throws SQLException {
//        String imageURL = "/images/" + name;
//        model.addAttribute("image", imageURL);
//        return "product-information";
//    }
//    @GetMapping("/main_{name}")
//    public String imagesForMain(Model model, @PathVariable String name) throws SQLException {
//        String imageURL = "/images/" + name;
//        model.addAttribute("image", imageURL);
//        return "hello";
//    }
}
