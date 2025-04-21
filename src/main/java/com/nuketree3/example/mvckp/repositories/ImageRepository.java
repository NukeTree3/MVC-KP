package com.nuketree3.example.mvckp.repositories;

import com.nuketree3.example.mvckp.model.images.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query(value = "SELECT path FROM photos WHERE product_id = :productId", nativeQuery = true)
    String getPathByProductId(Long productId);
}
