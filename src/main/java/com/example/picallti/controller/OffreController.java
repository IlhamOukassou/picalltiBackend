package com.example.picallti.controller;

import com.example.picallti.model.Offre;
import com.example.picallti.model.User;
import com.example.picallti.service.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/offers/")
public class OffreController {

    @Autowired
    private OffreService offreService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public void addOffre(@RequestBody Offre offre){
        offreService.addOffre(offre);
    }

    @RequestMapping(value = "addWithImage",method = RequestMethod.POST)
    public void addOffreWithImage(@RequestBody Offre offre,
                         @RequestParam MultipartFile file){
        offreService.addOffre(offre);
    }

    // Setting the pp of the user :
    @GetMapping(value = "downloadImage")
    public ResponseEntity<?> downloadImage(@RequestParam int id){
        Optional<Offre> offredb = offreService.getOffreById(id);
        if (offredb.isPresent()){
            byte[] imageData = offreService.downloadImage(offredb.get().getImageData());
            return  ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("image/png"))
                    .body(imageData);
        }
        return null;
    }

    @RequestMapping("getAll")
    public Collection<Offre> getAllOffers(){
        return offreService.getAllOffers();
    }

    @RequestMapping("getAllByUser/{id}")
    public Collection<Offre> getAllOffersByUser(@PathVariable int id){
        return offreService.getOffersByUser(id);
    }

    @RequestMapping("getById/{id}")
    public Offre getOffreById(@PathVariable int id){

        return offreService.getOffreById(id).get();
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Offre updateOffre(@RequestBody Offre offre){
        offreService.updateOffre(offre);
        return offre;
    }

    @RequestMapping(value = "update/{id}",method = RequestMethod.POST)
    public Offre updateOffreWithImage(@PathVariable int id,
                                      @RequestParam("image") MultipartFile file) throws IOException {
        if (offreService.getOffreById(id).isPresent()){
            Offre offredb = offreService.getOffreById(id).get();
           offreService.updateWithImage(offredb, file);
            return offredb;
        }else{
            throw new RuntimeException("Offre not found");
        }
    }

    @RequestMapping("remove/{id}")
    public void removeOffreById(@PathVariable int id)
    {
        offreService.removeById(id);
    }

    @RequestMapping("getByDate/{date}")
    public Collection<Offre> findOffreByLocaLDate(@PathVariable String date){
        return offreService.findOffreByLocaLDate(date);
    }

    @RequestMapping("getByDateDesc")
    public Collection<Offre> getOffreByDateDesc(){
        return offreService.findByDateDesc();
    }

    @RequestMapping("getByVille/{ville}")
    public Collection<Offre> getOffreByVille(@PathVariable String ville){
        return offreService.findOffreByVille(ville);
    }

    @RequestMapping(value = "offrebyvehiculetype")
    public Collection<Offre> findOffreByVehiculeType(@RequestParam String vehiculeTypeName){
        Collection<Offre> offres = offreService.getOffersByVehiculeType(vehiculeTypeName);
        if (offres.isEmpty()){
            return Collections.EMPTY_LIST ;
        }
        return offreService.getOffersByVehiculeType(vehiculeTypeName);
    }

    @GetMapping("filter")
    public Collection<Offre> filterOffresByPrix(@RequestParam float min, @RequestParam float max){
        return offreService.filterOffresByPrix(min,max);
    }
}
