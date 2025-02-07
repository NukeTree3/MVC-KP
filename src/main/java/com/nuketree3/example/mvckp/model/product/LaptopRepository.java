package com.nuketree3.example.mvckp.model.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {

    List<Laptop> findAllByName(String name);

    List<Laptop> id(long id);

    @Query(value = "SELECT count FROM storage WHERE product_name = :name", nativeQuery = true)
    int countByNameInStorage(@Param("name") String name);
}
