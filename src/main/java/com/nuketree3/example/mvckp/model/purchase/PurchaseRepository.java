package com.nuketree3.example.mvckp.model.purchase;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>{
    @Query(value = "SELECT MAX(basket_id) FROM purchase", nativeQuery = true)
    int findMaxBasketId();


    @Modifying
    @Transactional
    @Query(value = "UPDATE storage SET count = count - :count WHERE product_name = :productName", nativeQuery = true)
    void changeCountProductFromStorage(@Param("count") int count, @Param("productName") String productName);

    @Query(value = "SELECT count FROM storage WHERE product_name = :name", nativeQuery = true)
    int getCountOfProductFromStorage(@Param("name") String name);
}
