package com.nuketree3.example.mvckp.model.product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

    @Override
    public HashMap<String, String> getAllAttribute() {
        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("Производитель", producer);
        attributes.put("Модель процессора", processorModel);
        attributes.put("RAM", String.valueOf(ram));
        return attributes;
    }

}
