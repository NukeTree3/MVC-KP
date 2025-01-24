package com.nuketree3.example.mvckp.controller;

import com.nuketree3.example.mvckp.model.service.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class Controller {
    private final Service service;

    @GetMapping("/")
    public String hello(Model model) throws SQLException {
        model.addAttribute("products", service.productIdFromStorage());
        return "hello";
    }

    @GetMapping("/product/{id}")
    public String product(Model model, @PathVariable int id) throws SQLException {
        model.addAttribute("product", service.productIdFromStorage());
        return "product-information";
    }
}
