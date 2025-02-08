package com.nuketree3.example.mvckp.model.product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id); // Сравниваем по ID
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Хэш-код на основе ID
    }
}
