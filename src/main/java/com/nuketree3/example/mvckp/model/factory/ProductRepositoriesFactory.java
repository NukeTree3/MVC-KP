package com.nuketree3.example.mvckp.model.factory;

import com.nuketree3.example.mvckp.enums.ProductRepositoriesType;
import com.nuketree3.example.mvckp.model.product.Product;
import com.nuketree3.example.mvckp.repositories.LaptopRepository;
import com.nuketree3.example.mvckp.repositories.PersonalComputerRepository;
import com.nuketree3.example.mvckp.repositories.ProductBaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductRepositoriesFactory {

    private final LaptopRepository laptopRepository;
    private final PersonalComputerRepository personalComputerRepository;


    public ProductBaseRepository<? extends Product> createProductRepository(ProductRepositoriesType type) {
        ProductBaseRepository<? extends Product> productRepository = null;

        switch (type) {
            case LAPTOP_REPOSITORY -> productRepository = laptopRepository;
            case PERSONAL_COMPUTER_REPOSITORY -> productRepository = personalComputerRepository;
        }

        return productRepository;
    }
}
