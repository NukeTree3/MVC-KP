package com.nuketree3.example.mvckp.model.product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "pc")
public class PersonalComputer extends Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "pc_name")
    private String name;
    @Column(name = "producer")
    private String producer;
    @Column(name = "processor_model")
    private String processorModel;
    @Column(name = "ram")
    private int ram;
    @Column(name = "price")
    private int price;
    private String imagePath;

    public PersonalComputer(String namePC, String producer, int price, String processorModel, int ram ) {
        this.name = namePC;
        this.producer = producer;
        this.price = price;
        this.processorModel = processorModel;
        this.ram = ram;
    }

    @Override
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
