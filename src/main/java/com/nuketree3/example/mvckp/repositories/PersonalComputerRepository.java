package com.nuketree3.example.mvckp.repositories;

import com.nuketree3.example.mvckp.model.product.PersonalComputer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalComputerRepository extends JpaRepository<PersonalComputer, Long> {


    @Query(value = "SELECT * FROM pc WHERE lower(name) LIKE lower(concat('%', :name, '%'))", nativeQuery = true)
    List<PersonalComputer> findByNameLike(@Param("name") String name);

    List<PersonalComputer> id(long id);

    @Query(value = "SELECT count FROM storage WHERE product_name = ':name'", nativeQuery = true)
    Integer countByNameInStorage(@Param("name") String name);
}
