package com.nuketree3.example.mvckp.helper;

import java.util.HashMap;

public class CartHelper {

    public static void removeAll(HashMap<String, Integer> cart) {
        cart.clear();
    }

    public static void changeProductCount(HashMap<String, Integer> cart, String productName, int count) {
        if (cart != null) {
            if(cart.containsKey(productName)) {
                if(cart.get(productName) + count == 0){
                    cart.remove(productName);
                }
                else {
                    cart.put(productName, count);
                }
            }
            else if(count > 0){
                cart.put(productName, count);
            }
        }
    }

    public static int getProductCount(HashMap<String, Integer> cart, String productName) {
        return cart.getOrDefault(productName, 0);
    }

}
