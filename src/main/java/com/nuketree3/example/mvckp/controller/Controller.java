package com.nuketree3.example.mvckp.controller;

import com.nuketree3.example.mvckp.model.service.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class Controller{
    private final Service service;

    @GetMapping("/")
    public String hello(Model model) throws SQLException {
        model.addAttribute("products", service.getProductList());
        return "hello";
    }

    @GetMapping("/product/{name}")
    public String product(Model model, @PathVariable String name) throws SQLException {
        model.addAttribute("product", service.getProduct(name));
        return "product-information";
    }

    @GetMapping("/{name}")
    public String imagesForProductInformation(Model model, @PathVariable String name) throws SQLException {
        String imageURL = "/images/" + name;
        model.addAttribute("image", imageURL);
        return "product-information";
    }
    @GetMapping("/main_{name}")
    public String imagesForMain(Model model, @PathVariable String name) throws SQLException {
        String imageURL = "/images/" + name;
        model.addAttribute("image", imageURL);
        return "hello";
    }
}
