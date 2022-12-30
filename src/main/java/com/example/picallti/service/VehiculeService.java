package com.example.picallti.service;

import com.example.picallti.model.Vehicule;
import com.example.picallti.repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class VehiculeService {

    @Autowired
    public VehiculeRepository vehiculeRepository;

    public void addVehicule(Vehicule vehicule){
        vehiculeRepository.save(vehicule);
    }

    public Collection<Vehicule> getAllVehicules(){
        return vehiculeRepository.findAll();
    }

    public Vehicule getLast(){
        //int number = (int)vehiculeRepository.count();
        return vehiculeRepository.findTopByOrderByIdDesc();
    }
    public void removeVehiculeById(int id){
        vehiculeRepository.deleteById(id);
    }
}
