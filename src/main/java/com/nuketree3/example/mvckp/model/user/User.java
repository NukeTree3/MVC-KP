package com.nuketree3.example.mvckp.model.user;

import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate birthday;
    private String phone;

    public User(String firstName, String lastName, String email, String password, LocalDate birthday, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.phone = phone;
    }
}
