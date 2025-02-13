package com.nuketree3.example.mvckp.model.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {

    @Query(value = "SELECT * FROM laptop WHERE lower(name) LIKE lower(concat('%', :name, '%'))", nativeQuery = true)
    List<Laptop> findByNameLike(@Param("name") String name);

    List<Laptop> id(long id);

    @Query(value = "SELECT count FROM storage WHERE product_name = :name", nativeQuery = true)
    Integer countByNameInStorage(@Param("name") String name);

}
