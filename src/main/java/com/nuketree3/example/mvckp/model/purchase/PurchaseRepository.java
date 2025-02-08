package com.nuketree3.example.mvckp.model.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>{
    @Query(value = "SELECT MAX(basket_id) FROM purchase", nativeQuery = true)
    int findMaxBasketId();
}
