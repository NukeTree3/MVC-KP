package com.nuketree3.example.mvckp.model.user;

import com.nuketree3.example.mvckp.model.product.Product;
import com.nuketree3.example.mvckp.model.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ProductService productService;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model) throws SQLException {
        if(!userService.createUser(user)) {
            model.addAttribute("error", "Username or password is incorrect");
            return "registration";
        }
        return "redirect:/login";
    }

//    @GetMapping("/search")
//    @ResponseBody
//    public List<Product> searchProducts(@RequestParam(value = "query", required = false) String query, Model model) {
//        return productService.searchProductByName(query);
//    }

    @GetMapping("/activationcode/{code}")
    public String activationcode(@PathVariable String code, Model model) {
        boolean isActivated = userService.activateUser(code);
        if(isActivated) {
            model.addAttribute("message", "Activated" );
        }
        else {
            model.addAttribute("message", "Error" );
        }
        return "login";
    }


//    @GetMapping("/secured")
//    public String secured(Principal principal) {
//        if(principal==null) return null;
//        return principal.getName();
//    }

}
