package com.nuketree3.example.mvckp.service;

import com.nuketree3.example.mvckp.enums.ProductRepositoriesType;
import com.nuketree3.example.mvckp.model.cart.Cart;
import com.nuketree3.example.mvckp.model.factory.ProductRepositoriesFactory;
import com.nuketree3.example.mvckp.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ProductService{

    private final ProductRepositoriesFactory productRepositoriesFactory;
    private final CartService cartService;

    public List<Product> searchProductByName(String query){
        List<Product> products = new ArrayList<>();
        if(query==null){
            for(ProductRepositoriesType type : ProductRepositoriesType.values()){
                products.addAll(productRepositoriesFactory.createProductRepository(type).findAll());
            }
        }
        else {
            for(ProductRepositoriesType type : ProductRepositoriesType.values()){
                products.addAll(productRepositoriesFactory.createProductRepository(type).findByNameLike(query));
            }
        }
        return products;
    }

    public Product getProductById(Long id){
        ArrayList<Product> result = new ArrayList<>();
        for(ProductRepositoriesType type : ProductRepositoriesType.values()){
            productRepositoriesFactory.createProductRepository(type).findById(Math.toIntExact(id)).ifPresent(result::add);
        }
        return result.isEmpty() ? null : result.get(0);
    }

    public Product getProductByName(String productName){
        ArrayList<Product> result = new ArrayList<>();
        for(ProductRepositoriesType type : ProductRepositoriesType.values()){
            productRepositoriesFactory.createProductRepository(type).findByName(productName).ifPresent(result::add);
        }
        return result.isEmpty() ? null : result.get(0);
    }

    public int totalCost(String username){
        int total = 0;
        Cart tempCart = cartService.getUserCart(username);
        if(tempCart !=null && tempCart.getProductIds() != null){
            for(String productName : tempCart.getProductIds().keySet()){
                total += getProductPrice(productName) * tempCart.getProductIds().get(productName);
            }
        }
        return total;
    }

    public int getProductPrice(String productName){
        for(ProductRepositoriesType type : ProductRepositoriesType.values()){
            Product product = productRepositoriesFactory.createProductRepository(type).findByName(productName).orElse(null);
            if(product != null){
                return product.getPrice();
            }
        }
        return 0;
    }

    public int getCountProductByName(String name){
        if (name == null) return 0;
        try {
            for(ProductRepositoriesType type : ProductRepositoriesType.values()){
                if(productRepositoriesFactory.createProductRepository(type).countByNameInStorage(name) != 0 && productRepositoriesFactory.createProductRepository(type) != null){
                    return productRepositoriesFactory.createProductRepository(type).countByNameInStorage(name);
                }
            }

        }catch (Exception e){
            return 0;
        }
        return 0;
    }
}
