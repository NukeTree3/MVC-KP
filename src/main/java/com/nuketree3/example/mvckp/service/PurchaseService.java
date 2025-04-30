package com.nuketree3.example.mvckp.service;

import com.nuketree3.example.mvckp.model.purchase.Purchase;
import com.nuketree3.example.mvckp.repositories.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class PurchaseService {
    private final static boolean CAN_ORDER_IF_COUNT_IN_BASKET_MORE_THAN_COUNT_IS_STORAGE = false;
    private final PurchaseRepository purchaseRepository;
    private final ProductService productService;

    public boolean createOrder(HashMap<String, Integer> purchases, Long userID) {
        try {
            if (purchases.isEmpty()){
                return false;
            }
            int maxBasketID = 0;
            try {
                maxBasketID = purchaseRepository.findMaxBasketId() + 1;
            } catch (Exception e) {
                log.info(e.getMessage());
            }
            LocalDate date = LocalDate.now();
            int finalMaxBasketID = maxBasketID;

            if(!CAN_ORDER_IF_COUNT_IN_BASKET_MORE_THAN_COUNT_IS_STORAGE){
                purchases.forEach((product, count) -> {
                    if (count > purchaseRepository.getCountOfProductFromStorage(product)) {
                        throw new RuntimeException("ERROR");
                    }
                });
            }

            purchases.forEach((product, count) -> {
                Purchase purchase = new Purchase();
                purchase.setBasketId((long) finalMaxBasketID);
                purchase.setPurchaser(userID);
                purchase.setProductId(productService.searchProductByName(product).get(0).getId());
                purchase.setDate(date);
                purchaseRepository.changeCountProductFromStorage(count, product);
                purchase.setCount(count);
                purchaseRepository.save(purchase);
            });
            return true;
        }catch (Exception e){
            log.info(e.getMessage());
            return false;
        }
    }
}
