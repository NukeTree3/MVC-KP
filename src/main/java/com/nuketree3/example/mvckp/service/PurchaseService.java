package com.nuketree3.example.mvckp.service;

import com.nuketree3.example.mvckp.model.product.Product;
import com.nuketree3.example.mvckp.model.purchase.Purchase;
import com.nuketree3.example.mvckp.repositories.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final static boolean CAN_ORDER_IF_COUNT_IN_BASKET_MORE_THAN_COUNT_IS_STORAGE = false;
    private final PurchaseRepository purchaseRepository;

    public boolean createOrder(HashMap<Product, Integer> purchases, Long userID) {
        try {
            if (purchases.isEmpty()){
                System.out.println("isEmpty");
                return false;
            }
            int maxBasketID = 0;
            try {
                maxBasketID = purchaseRepository.findMaxBasketId() + 1;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            LocalDate date = LocalDate.now();
            int finalMaxBasketID = maxBasketID;

            if(!CAN_ORDER_IF_COUNT_IN_BASKET_MORE_THAN_COUNT_IS_STORAGE){
                purchases.forEach((product, count) -> {
                    if (count > purchaseRepository.getCountOfProductFromStorage(product.getName())) {
                        throw new RuntimeException("ERROR");
                    }
                });
            }

            purchases.forEach((product, count) -> {
                Purchase purchase = new Purchase();
                purchase.setBasketId((long) finalMaxBasketID);
                purchase.setPurchaser(userID);
                purchase.setProductId(product.getId());
                purchase.setDate(date);
                purchaseRepository.changeCountProductFromStorage(count, product.getName());
                purchase.setCount(count);
                purchaseRepository.save(purchase);
            });
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
