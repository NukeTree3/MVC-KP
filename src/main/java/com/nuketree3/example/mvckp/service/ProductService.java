package com.nuketree3.example.mvckp.service;

import com.nuketree3.example.mvckp.repositories.LaptopRepository;
import com.nuketree3.example.mvckp.repositories.PersonalComputerRepository;
import com.nuketree3.example.mvckp.model.product.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println("name " + name);
        if (name == null) return 0;
        System.out.println(1);
        try {
            if(laptopRepository.countByNameInStorage(name) != 0 && laptopRepository.countByNameInStorage(name) != null) return laptopRepository.countByNameInStorage(name);
            System.out.println(2);
            if(personalComputerRepository.countByNameInStorage(name) != 0 && personalComputerRepository.countByNameInStorage(name) != null) return personalComputerRepository.countByNameInStorage(name);
            System.out.println(3);
        }catch (Exception e){
            System.out.println(4);
            return 0;
        }

        return 0;
    }
}
