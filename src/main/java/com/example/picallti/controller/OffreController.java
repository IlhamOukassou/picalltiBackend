package com.example.picallti.controller;

import com.example.picallti.model.Offre;
import com.example.picallti.service.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping("/offers/")
public class OffreController {

    @Autowired
    private OffreService offreService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public void addOffre(@RequestBody Offre offre){
        offreService.addOffre(offre);
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

        return offreService.getOffreById(id);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Offre updateOffre(@RequestBody Offre offre){
        offreService.updateOffre(offre);
        return offre;
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
}
