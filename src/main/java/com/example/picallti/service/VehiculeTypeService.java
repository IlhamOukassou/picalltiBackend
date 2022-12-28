package com.example.picallti.service;

import com.example.picallti.model.VehiculeType;
import com.example.picallti.repository.VehiculeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculeTypeService {
    private final VehiculeTypeRepository vehiculeTypeRepository;


    @Autowired
    public VehiculeTypeService(VehiculeTypeRepository vehiculeTypeRepository) {
        this.vehiculeTypeRepository = vehiculeTypeRepository;
    }

    public List<VehiculeType> getVehiculeType(){
        return vehiculeTypeRepository.findAll();
    }

    public VehiculeType getVehiculeTypeById(int id){
        return vehiculeTypeRepository.findById(id).get();
    }

    public Optional<VehiculeType> getVehiculeTypeByName(String nom){
        return  vehiculeTypeRepository.findVehiculeTypeByName(nom);
    }
}
