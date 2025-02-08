package com.nuketree3.example.mvckp.model.purchase;

import com.nuketree3.example.mvckp.model.product.Product;
import com.nuketree3.example.mvckp.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public boolean createOrder(HashMap<Product, Integer> purchases, Long userID) {
        try {
            int maxBasketID =  purchaseRepository.findMaxBasketId() + 1;
            LocalDate date = LocalDate.now();
            purchases.forEach((product, count) -> {
                Purchase purchase = new Purchase();
                purchase.setBasketId((long) maxBasketID);
                purchase.setPurchaser(userID);
                purchase.setProductId(product.getId());
                purchase.setDate(date);
                purchase.setCount(count);
                purchaseRepository.save(purchase);
            });
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
