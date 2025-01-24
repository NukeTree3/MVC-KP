package com.nuketree3.example.mvckp.model.product;

import lombok.Data;

@Data
public abstract class Product {
    private String name;
    private String producer;
    private int price;

    Product(String name, String producer, int price) {
        this.name = name;
        this.producer = producer;
        this.price = price;
    }
}
