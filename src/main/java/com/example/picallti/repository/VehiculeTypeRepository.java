package com.example.picallti.repository;

import com.example.picallti.model.VehiculeType;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehiculeTypeRepository extends JpaRepository<VehiculeType,Integer> {
    Optional<VehiculeType> findVehiculeTypeByName(String name);
    Boolean existsVehiculeTypeByName(String name);


}
