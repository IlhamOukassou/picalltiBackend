package com.example.picallti.service;

import com.example.picallti.model.VehiculeType;
import com.example.picallti.repository.VehiculeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class VehiculeTypeService {
    @Autowired
    public VehiculeTypeRepository vehiculeTypeRepository;

    public Collection<VehiculeType> getVehiculeType(){

        return vehiculeTypeRepository.findAll();
    }

    public Optional<VehiculeType> getVehiculeTypeById(int id){

        return vehiculeTypeRepository.findById(id);
    }

    public Optional<VehiculeType> getVehiculeTypeByName(String name){
        return  vehiculeTypeRepository.findVehiculeTypeByName(name);
    }
}
