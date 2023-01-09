package com.example.picallti.service;

import com.example.picallti.model.ImageData;
import com.example.picallti.model.User;
import com.example.picallti.repository.ImageDataRepository;
import com.example.picallti.util.ImageDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@Service
public class ImageDataService {
    @Autowired
    private ImageDataRepository imageDataRepository;

    public String UploadImage(MultipartFile file ) throws IOException {
       ImageData imageData = imageDataRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageDataUtil.compressImage(file.getBytes())).build());
       if(imageData!=null){
           return "File uploaded successfully !! : " + file.getOriginalFilename();

       }
       return null;
    }

    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImageData = imageDataRepository.findByName(fileName);
        byte[] images = ImageDataUtil.decompressImage(dbImageData.get().getImageData());
        return images;
    }

    public byte[] downloadImageById(Long id){
        Optional<ImageData> dbImageData = imageDataRepository.findById(id);
        byte[] images = ImageDataUtil.decompressImage(dbImageData.get().getImageData());
        return images;
    }
    public Collection<ImageData> getAllImages(){
        return imageDataRepository.findAll();
    }

    public Optional<ImageData> getImageById(Long id){
        return imageDataRepository.findById(id);
    }

    public Optional<ImageData> getImageByName(String name){
        return imageDataRepository.findByName(name);
    }

    public void removeImageById(Long id){
        imageDataRepository.deleteById(id);
    }

    public void removeImageByName(String name){
        imageDataRepository.deleteByName(name);
    }
}
