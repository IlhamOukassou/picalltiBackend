package com.example.picallti.controller;

import com.example.picallti.model.Favoris;
import com.example.picallti.service.FavorisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/favoris")
@CrossOrigin(origins="*")
public class FavorisController {
    private final FavorisService favorisService;

    public FavorisController(FavorisService favorisService) {
        this.favorisService = favorisService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Favoris>> getAllFavoris(){
        List<Favoris> favoris = favorisService.findAllFavoris();
        return new ResponseEntity<>(favoris, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Favoris> getFavorisById(@PathVariable("id") int id){
        Favoris favoris = favorisService.findFavorisById(id);
        return new ResponseEntity<>(favoris,HttpStatus.OK);
    }

    @GetMapping("/findallbyuser")
    public ResponseEntity<Optional<List<Favoris>>> getFavorisByUser(@RequestParam int id){
        Optional<List<Favoris>> favorisByUser = favorisService.findByUser(id);
        return new ResponseEntity<Optional<List<Favoris>>>(favorisByUser,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Favoris> addFavoris(@RequestBody Favoris favoris){
        Favoris newFavoris = favorisService.addFavoris(favoris);
        return new ResponseEntity<>(newFavoris,HttpStatus.CREATED);
    }

    /*@PutMapping("/update")
    public ResponseEntity<Favoris> updateFavoris(@RequestBody Favoris favoris){
        Favoris updateFavoris = favorisService.updateFavoris(favoris);
        return new ResponseEntity<>(updateFavoris,HttpStatus.OK);
    }*/

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFavoris(@PathVariable("id") int id){
        favorisService.deleteFavoris(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
