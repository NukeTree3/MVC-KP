package com.nuketree3.example.mvckp.service;

import com.nuketree3.example.mvckp.repositories.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public String getPathByProductID(Long productID) {
        return imageRepository.getPathByProductId(productID);
    }
}
