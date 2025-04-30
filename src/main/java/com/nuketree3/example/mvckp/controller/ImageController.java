package com.nuketree3.example.mvckp.controller;

import com.nuketree3.example.mvckp.service.DropboxService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class ImageController {

    @Autowired
    private DropboxService dropboxService;

    @GetMapping("/images/download/{path}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable("path") String path) {
        try{
            byte[] imagesByte = dropboxService.downloadFile(path);
            if(imagesByte.length == 0){
                return null;
            }
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagesByte);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
