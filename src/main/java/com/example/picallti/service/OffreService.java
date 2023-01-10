package com.example.picallti.service;

import com.example.picallti.model.Offre;
import com.example.picallti.model.User;
import com.example.picallti.model.VehiculeType;
import com.example.picallti.repository.OffreRepository;
import com.example.picallti.repository.UserRepository;
import com.example.picallti.repository.VehiculeTypeRepository;
import com.example.picallti.util.ImageDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class OffreService {

    @Autowired
    private OffreRepository offreRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VehiculeTypeRepository vehiculeTypeRepository;

    public void addOffre(Offre offre) {
        offreRepository.save(offre);
    }

    public Collection<Offre> getAllOffers() {
        return offreRepository.findAll();
    }

    public Optional<Offre> getOffreById(int id) {
        System.out.println("****************");
        System.out.println(id);
        System.out.println("****************");
        return offreRepository.findById(id);
    }

    public Collection<Offre> getOffersByUser(int id) {
        User user = userRepository.findById(id).get();
        if (user != null) {
            return offreRepository.findOffresByUser(user);
        }
        return null;
    }

    public Collection<Offre> getOffersByVehiculeType(String vehiculeTypeName){
        Optional<VehiculeType> vehiculeType = vehiculeTypeRepository.findVehiculeTypeByName(vehiculeTypeName);
        if(vehiculeType.isPresent()){
            System.out.println("****************");
            System.out.println(vehiculeTypeName);
            System.out.println("****************");
            Collection<Offre> offres = offreRepository.findOffresByVehiculeVehiculeType(vehiculeType.get());
            return offres;
        }
        return Collections.EMPTY_LIST;

    }

    public byte[] downloadImage(byte[] imageData){
        byte[] images = ImageDataUtil.decompressImage(imageData);
        return images;
    }

    public void updateOffre(Offre offre) {
        Offre offre1 = offreRepository.findById(offre.getId()).get();
        if (offre1 != null) {
            offre1.setDescription(offre.getDescription());
            offre1.setImageId(offre.getImageId());
            offre1.setOperation(offre.getOperation());
            offre1.setPrix(offre.getPrix());
            offre1.setTime(offre.getTime());
            offre1.setImageData(offre.getImageData());
            offre1.setUser(offre.getUser());
            offre1.setlocaLDate(offre.getlocaLDate());
            offre1.setTitre(offre.getTitre());
            offre1.setVehicule(offre.getVehicule());
            offreRepository.save(offre1);
        }
    }

    public void updateWithImage(Offre offre, MultipartFile file) throws IOException {
        Offre offre1 = offreRepository.findById(offre.getId()).get();
        if (offre1 != null) {
            offre1.setDescription(offre.getDescription());
            offre1.setOperation(offre.getOperation());
            offre1.setPrix(offre.getPrix());
            offre1.setTime(offre.getTime());
            offre1.setImageData(ImageDataUtil.compressImage(file.getBytes()));
            offre1.setUser(offre.getUser());
            offre1.setlocaLDate(offre.getlocaLDate());
            offre1.setTitre(offre.getTitre());
            offre1.setVehicule(offre.getVehicule());
            offreRepository.save(offre1);
        }
    }

//    public Collection<Offre> findByOfferLocate(String date) {
//        return offreRepository.findByOfferLocate(date);
//    }

    public void removeById(int id){
        offreRepository.deleteById(id);
    }

    public Collection<Offre> findByDateDesc() {
        return offreRepository.findByDateDesc();
    }

    public Collection<Offre> findOffreByLocaLDate(String date) {
        return offreRepository.findOffreByLocaLDate(date);
    }

    public Collection<Offre> findOffreByVille(String ville) {
        return offreRepository.findOffreByVille(ville);
    }
    public Collection<Offre> filterOffresByPrix(float min, float max){
        return offreRepository.filterOffresByPrix(min,max);
    }

    public Collection<Offre> findInTitleLike(String titre){
        return offreRepository.getOffreByTitreContainingIgnoreCase(titre);
    }

}
