package com.nuketree3.example.mvckp.model.product;

import lombok.Data;

@Data
public class PersonalComputer {
    private String nameLaptop;
    private String producer;
    private String processorModel;
    private int ram;
    private int price;

    public PersonalComputer(String namePersonalComputer, String producer, String processorModel, int ram, int price ) {
        this.nameLaptop = nameLaptop;
        this.producer = producer;
        this.processorModel = processorModel;
        this.ram = ram;
        this.price = price;
    }
}
