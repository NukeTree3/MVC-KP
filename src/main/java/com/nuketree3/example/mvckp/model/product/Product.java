package com.nuketree3.example.mvckp.model.product;

import lombok.Data;

@Data
public abstract class Product {
    private String name;
    private String producer;
    private int price;
    private int ram;
    private String processorModel;
    private String imagePath;
}
