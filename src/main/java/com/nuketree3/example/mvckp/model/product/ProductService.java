package com.nuketree3.example.mvckp.model.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService{


    private final LaptopRepository laptopRepository;

    private final PersonalComputerRepository personalComputerRepository;

    public List<Product> searchProductByName(String query){
        List<Product> products = new ArrayList<>();
        if(query==null){
            products.addAll(laptopRepository.findAll());
            products.addAll(personalComputerRepository.findAll());
        }
        else {
            products.addAll(laptopRepository.findByNameLike(query));
            products.addAll(personalComputerRepository.findByNameLike(query));
        }
        return products;
    }

    public Product getProductById(Long id){
        if(laptopRepository.findById(id).isPresent()) return laptopRepository.findById(id).orElse(null);
        if(personalComputerRepository.findById(id).isPresent()) return personalComputerRepository.findById(id).orElse(null);
        return null;
    }

    public int getCountProductByName(String name){
        if (name == null) return 0;
        if(laptopRepository.countByNameInStorage(name) != 0) return laptopRepository.countByNameInStorage(name);
        if(personalComputerRepository.countByNameInStorage(name) != 0) return personalComputerRepository.countByNameInStorage(name);
        return 0;
    }
}
