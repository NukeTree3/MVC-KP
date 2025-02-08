package com.nuketree3.example.mvckp.controller;

import com.nuketree3.example.mvckp.model.product.Product;
import com.nuketree3.example.mvckp.model.product.ProductService;
import com.nuketree3.example.mvckp.model.purchase.BasketService;
import com.nuketree3.example.mvckp.model.purchase.PurchaseService;
import com.nuketree3.example.mvckp.model.service.Service;
import com.nuketree3.example.mvckp.model.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.sql.SQLException;
import java.util.Map;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class Controller{
    private final Service service;
    private final ProductService productService;
    private final BasketService basketService;
    private final PurchaseService purchaseService;
    private final UserService userService;

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

//    @PostMapping("/")
//    public String productsWithQuery(@RequestParam(value = "query", required = false) String query, Model model){
//        model.addAttribute("products", productService.searchProductByName(query));
//        return "hello";
//    }

    @GetMapping("/product/{id}")
    public String product(Model model, @PathVariable Long id) throws SQLException {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("countProductFromBasket", basketService.getProductCount(productService.getProductById(id)));
        model.addAttribute("count", productService.getCountProductByName(productService.getProductById(id).getName()));
        System.out.println("from product "+productService.getProductById(id).hashCode());
        return "product-information";
    }

    @PostMapping("/product/{id}")
    public String addToCart(@PathVariable Long id, @RequestParam("quantity") int quantity) {
        System.out.println(id + " " + quantity);
        basketService.addProduct(productService.getProductById(id), quantity);
        System.out.println("from product post"+productService.getProductById(id).hashCode());
//        for(Product c: basket.getProducts().keySet()){
//            System.out.println(c.getName() + " " + basket.getProducts().get(c));
//        }
        return "redirect:/product/" + id;
    }

    @GetMapping("/basket")
    public String basket(Model model) {
        for(Product c: basketService.getProducts().keySet()){
            System.out.println(c.getName() + " " + basketService.getProducts().get(c));
            System.out.println("from basket"+c.hashCode());
        }
        model.addAttribute("basket", basketService);
        return "basket";
    }

    @GetMapping("/order")
    public String orderFromBasket(Model model, Principal principal) {
        if(purchaseService.createOrder(basketService.getProducts(), userService.getUserId(principal.getName()))){
            basketService.removeAll();
            model.addAttribute("status", "success");
        }
        else{
            model.addAttribute("status", "failed");
        }
        return "order";
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
