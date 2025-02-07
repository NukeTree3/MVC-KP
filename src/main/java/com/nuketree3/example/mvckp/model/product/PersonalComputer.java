package com.nuketree3.example.mvckp.model.product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "pc")
public class PersonalComputer extends Product {
    @Column(name = "producer")
    private String producer;
    @Column(name = "processor_model")
    private String processorModel;
    @Column(name = "ram")
    private int ram;
    @Column(name = "price")
    private int price;
//    private String imagePath;

//    public PersonalComputer(String namePC, String producer, int price, String processorModel, int ram ) {
//        super(name) = namePC;
//        this.producer = producer;
//        this.price = price;
//        this.processorModel = processorModel;
//        this.ram = ram;
//    }

    //    @Override
//    public void setImagePath(String imagePath) {
//        this.imagePath = imagePath;
//    }
    @Override
    public HashMap<String, String> getAllAttribute() {
        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("Название", getName());
        attributes.put("Производитель", producer);
        attributes.put("Модель процессора", processorModel);
        attributes.put("RAM", String.valueOf(ram));
        attributes.put("Цена", String.valueOf(price));
        return attributes;
    }

}
