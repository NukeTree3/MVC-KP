package com.nuketree3.example.mvckp.controller;

import com.nuketree3.example.mvckp.model.comment.Comment;
import com.nuketree3.example.mvckp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.SQLException;
import java.util.Objects;

import static com.nuketree3.example.mvckp.enums.Role.SecurityConstants.ROLE_USER_STRING;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class Controller{
    private final ProductService productService;
    private final CartService cartService;
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
    public String product(Model model, @PathVariable Long id, Principal principal) throws SQLException {
        model.addAttribute("product", productService.getProductById(id));
        if(principal != null) {
            model.addAttribute("userID", userService.getUserId(principal.getName()));
            Integer tempInt = cartService.getProductCount(productService.getProductById(id).getName(), principal.getName());
            model.addAttribute("countProductFromBasket", Objects.requireNonNullElse(tempInt, 0));
        }else{
            model.addAttribute("countProductFromBasket", 0);
        }
        model.addAttribute("count", productService.getCountProductByName(productService.getProductById(id).getName()));
        if(!Double.isNaN(commentsService.getAverageRanting(id))){
            model.addAttribute("rating", commentsService.getAverageRanting(id));
        }else{
            model.addAttribute("rating", 0);
        }
        model.addAttribute("comments", commentsService.getCommentsByProductID(id));
        model.addAttribute("serviceToComment", userService);
        model.addAttribute("imgService", imageService);
        return "product-information";
    }

    @PostMapping("/product/{id}")
    public String addToCart(@PathVariable Long id, @RequestParam("quantity") int quantity, Principal principal) {
        cartService.changeProductCountToUserCart(productService.getProductById(id).getName(), quantity, principal.getName());
        return "redirect:/product/" + id;
    }

    @PostMapping("/product/{id}/add-comment")
    public String addComment(@PathVariable Long id, @RequestParam("commentText") String commentText, Principal principal, @RequestParam("rating") int rating) {
        commentsService.addComments(new Comment(userService.getUserId(principal.getName()), id, rating, commentText));
        return "redirect:/product/" + id;
    }

    @PostMapping("/product/{id}/delete-comment/{commentId}")
    public String deleteComment(@PathVariable("id") Long id, @PathVariable("commentId") Long commentId,  Principal principal) {
        commentsService.deleteComment(commentId, principal.getName());
        return "redirect:/product/" + id;
    }

    @GetMapping("/basket")
    @PreAuthorize("hasRole('"+ROLE_USER_STRING+"')")
    public String basket(Model model, Principal principal) {
        model.addAttribute("basket", cartService.getUserCart(principal.getName()).getProductIds());
        model.addAttribute("imgService", imageService);
        model.addAttribute("productService", productService);
        model.addAttribute("totalCost", productService.totalCost(principal.getName()));
        return "basket";
    }

    @GetMapping("/order")
    @PreAuthorize("hasRole('"+ROLE_USER_STRING+"')")
    public String orderFromBasket(Model model, Principal principal) {
        if(purchaseService.createOrder(cartService.getUserCart(principal.getName()).getProductIds(), userService.getUserId(principal.getName()))){
            cartService.removeAll(principal.getName());
            model.addAttribute("status", "success");
        }
        else{
            model.addAttribute("status", "failed");
            model.addAttribute("error", "error");
        }
        return "order";
    }
}
