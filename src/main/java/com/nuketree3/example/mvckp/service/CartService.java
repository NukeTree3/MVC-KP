package com.nuketree3.example.mvckp.service;

import com.nuketree3.example.mvckp.helper.CartHelper;
import com.nuketree3.example.mvckp.model.cart.Cart;
import com.nuketree3.example.mvckp.model.product.Product;
import com.nuketree3.example.mvckp.repositories.CartRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public void changeProductCountToUserCart(String productName, int productCount, String username) {
        Cart tempCart = cartRepository.findByUsername(username).orElse(null);
        if (tempCart == null) {
            tempCart = new Cart();
            tempCart.setUsername(username);
            tempCart.setProductIds(new HashMap<>());
            tempCart.getProductIds().put(productName, productCount);
        }
        CartHelper.changeProductCount(tempCart.getProductIds(), productName, productCount);
        cartRepository.save(tempCart);
    }

    public void removeAll(String username) {
        cartRepository.deleteByUsername(username);
    }

    public Cart getUserCart(String username) {
        return cartRepository.findByUsername(username).orElse(null);
    }

    public Integer getProductCount(String productName, String username) {
        if(getUserCart(username) != null && getUserCart(username).getProductIds() != null) {
            return getUserCart(username).getProductIds().get(productName);
        }
        return 0;
    }

}
