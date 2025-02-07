package com.nuketree3.example.mvckp.model.purchase;

import com.nuketree3.example.mvckp.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Basket {

    private List<Product> products;

    public void addProduct(Product product) {
        products.add(product);
    }
}
