package com.nuketree3.example.mvckp.model.images;

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
