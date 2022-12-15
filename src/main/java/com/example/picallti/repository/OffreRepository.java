package com.example.picallti.repository;

import com.example.picallti.model.Offre;
import com.example.picallti.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface OffreRepository extends JpaRepository<Offre,Integer> {
    public Collection<Offre> findOffresByUser(User user);
}
