package com.nuketree3.example.mvckp.repositories;

import com.nuketree3.example.mvckp.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface ProductBaseRepository<T extends Product> extends JpaRepository<T, Integer> {
    List<T> findByNameLike(String name);

    Integer countByNameInStorage(String name);

    Optional<T> findByName(String name);
}
