package com.example.picallti.controller;

import com.example.picallti.service.ImageDataService;
import com.example.picallti.util.ImageDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/imageData/")
public class ImageDataController {
    @Autowired
    private ImageDataService imageDataService;
    @PostMapping("upload")
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file ) throws IOException {
        String uploadImage = imageDataService.UploadImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }
    @GetMapping("{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] imageData = imageDataService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
