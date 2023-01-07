package com.example.picallti.repository;

import com.example.picallti.model.Offre;
import com.example.picallti.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OffreRepository extends JpaRepository<Offre,Integer> {
    public Collection<Offre> findOffresByUser(User user);

    @Query("SELECT o FROM Offre o WHERE o.prix BETWEEN ?1 AND ?2")
    public Collection<Offre> filterOffresByPrix(float min,float max);
}
