package com.example.picallti.repository;

import com.example.picallti.model.Offre;
import com.example.picallti.model.User;
import com.example.picallti.model.VehiculeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OffreRepository extends JpaRepository<Offre,Integer> {
    public Collection<Offre> findOffresByUser(User user);
        public Collection<Offre> findOffresByVehiculeVehiculeType(VehiculeType vehiculeType);
}
