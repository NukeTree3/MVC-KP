package com.nuketree3.example.mvckp.model.product;

import lombok.experimental.SuperBuilder;

import java.util.HashMap;

@SuperBuilder
public class PersonalComputer extends Product{
    private int ram;
    private String processorModel;

    public PersonalComputer(String namePC, String producer, int price, String processorModel, int ram ) {
        super(namePC, producer, price);
        this.processorModel = processorModel;
        this.ram = ram;
    }

    public HashMap<String, String> getOtherAttributes() {
        HashMap<String, String> otherAttributes = new HashMap<>();
        otherAttributes.put("processorModel", processorModel);
        otherAttributes.put("ram", String.valueOf(ram));
        return otherAttributes;
    }
}
