package com.nuketree3.example.mvckp.controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/")
    public String hello() {
        return "hello";
    }
}
