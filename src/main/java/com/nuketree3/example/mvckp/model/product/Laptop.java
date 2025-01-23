package com.nuketree3.example.mvckp.model.product;

import lombok.Data;

@Data
public class Laptop {
    private String nameLaptop;
    private String producer;
    private String processorModel;
    private int ram;
    private int price;

    public Laptop(String nameLaptop, String producer, String processorModel, int ram, int price ) {
        this.nameLaptop = nameLaptop;
        this.producer = producer;
        this.processorModel = processorModel;
        this.ram = ram;
        this.price = price;
    }
}