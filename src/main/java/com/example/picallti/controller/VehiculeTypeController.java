package com.example.picallti.controller;

import com.example.picallti.model.VehiculeType;
import com.example.picallti.service.VehiculeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping("/vehiculetype")
public class VehiculeTypeController {

    @Autowired
    public VehiculeTypeService vehiculeTypeService;

    @GetMapping(value = "getAll")
    public Collection<VehiculeType> getVehiculeType(){
        return vehiculeTypeService.getVehiculeType();
    }

    @GetMapping(value = "getbyid")
    public VehiculeType getVehiculeTypeById(@RequestParam int id){
        if(vehiculeTypeService.getVehiculeTypeById(id).isPresent()){
            return vehiculeTypeService.getVehiculeTypeById(id).get();
        }else {
            return null;
        }
    }

    @GetMapping(value = "getbyname")
    public VehiculeType getVehiculeTypeByName(@RequestParam String name){
        if(vehiculeTypeService.getVehiculeTypeByName(name).isPresent()){
            return  vehiculeTypeService.getVehiculeTypeByName(name).get();
        }else{
            return null;
        }
    }
}
