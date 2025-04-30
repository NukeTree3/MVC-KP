package com.nuketree3.example.mvckp.controller;

import com.nuketree3.example.mvckp.service.AdminService;
import com.nuketree3.example.mvckp.model.user.User;
import com.nuketree3.example.mvckp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.SQLException;

import static com.nuketree3.example.mvckp.enums.Role.SecurityConstants.ROLE_NOT_ACTIVATED_STRING;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AdminService adminService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if(error != null) {
            model.addAttribute("loginError", "true");
        }
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


    @GetMapping("/activationcode/{code}")
    @PreAuthorize("hasRole('"+ROLE_NOT_ACTIVATED_STRING+"')")
    public String activationCode(@PathVariable String code, Model model) {
        boolean isActivated = userService.activateUser(code);
        if(isActivated) {
            model.addAttribute("message", "Activated" );
        }
        else {
            model.addAttribute("message", "Error" );
        }
        return "login";
    }

    @GetMapping("/user-account")
    public String userAccount(Model model, Principal principal){
        User user = userService.getUserByID(userService.getUserId(principal.getName()));
        model.addAttribute("firstname", user.getFirstName());
        model.addAttribute("lastname", user.getLastName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("phone", user.getPhone());
        model.addAttribute("birthdate", user.getBirthday());

        return "user-account";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin(Model model, Principal principal){
        return "admin";
    }

    @PostMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminQuery(Model model, @RequestParam("Query") String query){
        model.addAttribute("queryStatus", adminService.executeArbitrarySQL(query));
        return "admin";
    }
}
