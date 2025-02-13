package com.nuketree3.example.mvckp.model.product;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashMap;
import java.util.Objects;

@Data
@MappedSuperclass
public abstract class Product {
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
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
