package com.nuketree3.example.mvckp.model.purchase;

import com.nuketree3.example.mvckp.model.product.Product;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Data
public class BasketService {

    private final HashMap<Product, Integer> products = new HashMap<>();

    public void removeAll() {
        products.clear();
    }

    public void addProduct(Product product, int quantity) {
        if(products.containsKey(product)) {
            if(quantity == 0){
                products.remove(product);
            }
            else {
                products.put(product, quantity);
            }
        }
        else if(quantity > 0){
            products.put(product, quantity);
        }
    }

    public int getQuantity(Product product) {
        return products.getOrDefault(product, 0);
    }

    public int getProductCount(Product product) {
        return products.getOrDefault(product, 0);
    }
}
