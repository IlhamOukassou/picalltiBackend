package com.example.picallti.controller;

import com.example.picallti.model.Offre;
import com.example.picallti.model.User;
import com.example.picallti.repository.UserRepository;
import com.example.picallti.service.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping("/offers/")
public class OffreController {

    @Autowired
    private OffreService offreService;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public void addOffre(@RequestBody Offre offre){
        offreService.addOffre(offre);
    }

    @RequestMapping("getAll")
    public Collection<Offre> getAllOffers(){
        return offreService.getAllOffers();
    }

    @RequestMapping("getAllByUser")
    public Collection<Offre> getAllOffersByUser(@RequestParam int id){
        return offreService.getOffersByUser(id);
    }

    @RequestMapping("getById")
    public Offre getOffreById(@RequestParam int id){
        return offreService.getOffreById(id);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public void updateOffre(@RequestBody Offre offre){
        offreService.updateOffre(offre);
    }

    @RequestMapping("remove")
    public void removeOffreById(@RequestParam int id)
    {
        offreService.removeById(id);
    }
}
