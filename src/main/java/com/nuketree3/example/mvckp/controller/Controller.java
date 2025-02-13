package com.nuketree3.example.mvckp.controller;

import com.nuketree3.example.mvckp.model.comment.Comment;
import com.nuketree3.example.mvckp.model.comment.CommentsService;
import com.nuketree3.example.mvckp.model.images.ImageService;
import com.nuketree3.example.mvckp.model.product.ProductService;
import com.nuketree3.example.mvckp.model.purchase.BasketService;
import com.nuketree3.example.mvckp.model.purchase.PurchaseService;
import com.nuketree3.example.mvckp.model.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.sql.SQLException;

import static com.nuketree3.example.mvckp.model.enums.Role.SecurityConstants.ROLE_USER_STRING;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class Controller{
    private final ProductService productService;
    private final BasketService basketService;
    private final PurchaseService purchaseService;
    private final UserService userService;
    private final CommentsService commentsService;
    private final ImageService imageService;


    @GetMapping("/")
    public String products(@RequestParam(value = "query", required = false) String query, Model model) {
        model.addAttribute("products", productService.searchProductByName(query));
        model.addAttribute("imgService", imageService);
        return "hello";
    }

    @GetMapping("/product/{id}")
    public String product(Model model, @PathVariable Long id) throws SQLException {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("countProductFromBasket", basketService.getProductCount(productService.getProductById(id)));
        System.out.println(productService.getCountProductByName(productService.getProductById(id).getName()));
        model.addAttribute("count", productService.getCountProductByName(productService.getProductById(id).getName()));
        if(!Double.isNaN(commentsService.getAverageRanting(id))){
            model.addAttribute("rating", commentsService.getAverageRanting(id));
        }else{
            model.addAttribute("rating", "нет оценок");
        }
        model.addAttribute("comments", commentsService.getCommentsByProductID(id));
        model.addAttribute("serviceToComment", userService);
        model.addAttribute("imgService", imageService);
        return "product-information";
    }

    @PostMapping("/product/{id}")
    public String addToCart(@PathVariable Long id, @RequestParam("quantity") int quantity) {
        basketService.addProduct(productService.getProductById(id), quantity);
        return "redirect:/product/" + id;
    }

    @PostMapping("/product/{id}/add-comment")
    public String addComment(@PathVariable Long id, @RequestParam("commentText") String commentText, Principal principal, @RequestParam("rating") int rating) {
        commentsService.addComments(new Comment(userService.getUserId(principal.getName()), id, rating, commentText));
        return "redirect:/product/" + id;
    }

    @GetMapping("/basket")
    @PreAuthorize("hasRole('"+ROLE_USER_STRING+"')")
    public String basket(Model model) {
        model.addAttribute("basket", basketService);
        model.addAttribute("imgService", imageService);
        return "basket";
    }

    @GetMapping("/order")
    @PreAuthorize("hasRole('"+ROLE_USER_STRING+"')")
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
}
