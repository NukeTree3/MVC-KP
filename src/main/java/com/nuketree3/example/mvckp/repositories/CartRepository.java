package com.nuketree3.example.mvckp.repositories;

import com.nuketree3.example.mvckp.model.cart.Cart;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableRedisRepositories(basePackageClasses = CartRepository.class)
public interface CartRepository extends CrudRepository<Cart, String> {
    Optional<Cart> findByUsername(String username);

    void deleteByUsername(String username);

    List<Cart> getAllByUsername(String username);

}
