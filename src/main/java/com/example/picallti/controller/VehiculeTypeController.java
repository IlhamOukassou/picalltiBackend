package com.example.picallti.controller;

import com.example.picallti.model.VehiculeType;
import com.example.picallti.repository.VehiculeTypeRepository;
import com.example.picallti.service.VehiculeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehiculetype")
public class VehiculeTypeController {
    @Autowired
    private VehiculeTypeService vehiculeTypeService;

    @GetMapping(value = "getAll")
    public List<VehiculeType> getVehiculeType(){
        return vehiculeTypeService.getVehiculeType();
    }

    @GetMapping(value = "getbyid")
    public VehiculeType getVehiculeTypeById(@RequestParam int id){
        return vehiculeTypeService.getVehiculeTypeById(id);
    }

    @GetMapping(value = "getbyname")
    public Optional<VehiculeType> getVehiculeTypeByName(@RequestParam String nom){
        return  vehiculeTypeService.getVehiculeTypeByName(nom);
    }

}
