package com.example.picallti.controller;

import com.example.picallti.model.Vehicule;
import com.example.picallti.service.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping("/vehicule/")
public class VehiculeController {

    @Autowired
    public VehiculeService vehiculeService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public void addVehicule(@RequestBody Vehicule vehicule){
        vehiculeService.addVehicule(vehicule);
    }

    @RequestMapping(value = "getAll")
    public Collection<Vehicule> getAllvehicules(){
        return  vehiculeService.getAllVehicules();
    }

    @RequestMapping(value = "remove")
    public void removeVehicule(@RequestParam int id){
        vehiculeService.removeVehiculeById(id);
    }


}
