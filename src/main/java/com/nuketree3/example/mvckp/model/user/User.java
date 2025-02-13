package com.nuketree3.example.mvckp.model.user;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

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


    public User(String firstName, String lastName, String email, String password, LocalDate birthday, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.phone = phone;
    }

    public User() {

    }
}
