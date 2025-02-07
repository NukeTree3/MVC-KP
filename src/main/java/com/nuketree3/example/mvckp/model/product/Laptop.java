package com.nuketree3.example.mvckp.model.product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.HashSet;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "laptop")
public class Laptop extends Product {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(name = "name")
//    private String name;
    @Column(name = "producer")
    private String producer;
    @Column(name = "processor_model")
    private String processorModel;
    @Column(name = "ram")
    private int ram;
    @Column(name = "price")
    private int price;
//    private String imagePath;

//    public Laptop(String nameLaptop, String producer, int price, String processorModel, int ram ) {
//        this.name = nameLaptop;
//        this.producer = producer;
//        this.price = price;
//        this.processorModel = processorModel;
//        this.ram = ram;
//    }
//
//    @Override
//    public void setImagePath(String imagePath) {
//        this.imagePath = imagePath;
//    }
    @Override
    public HashMap<String, String> getAllAttribute(){
        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("Название", getName());
        attributes.put("Производитель", producer);
        attributes.put("Модель процессора", processorModel);
        attributes.put("RAM", String.valueOf(ram));
        attributes.put("Цена", String.valueOf(price));
        return attributes;
    }
}