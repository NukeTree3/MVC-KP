package com.nuketree3.example.mvckp.model.product;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashMap;

@Data
@MappedSuperclass
//@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {
//    private String name;
//    private String producer;
//    private int price;
//    private int ram;
//    private String processorModel;
//    private String imagePath;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;

    public abstract HashMap<String, String> getAllAttribute();
}
