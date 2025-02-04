package com.nuketree3.example.mvckp.model.user;

import com.nuketree3.example.mvckp.model.enums.Role;
//import jakarta.persistence.ElementCollection;
//import jakarta.persistence.FetchType;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Table(name = "purchaser")
@Entity
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "login")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "birthdate")
    private LocalDate birthday;
    @Column(name = "phone_number")
    private String phone;
////    private boolean active;
//    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//    private Set<Role> roles = new HashSet<>();
//    private LocalDateTime dateOfCreation;

    public User(String firstName, String lastName, String email, String password, LocalDate birthday, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.phone = phone;
//        init();
//        roles.add(Role.ROLE_USER);
    }

    public User() {

    }

//
//    private void init() {
//        dateOfCreation = LocalDateTime.now();
//    }
}
