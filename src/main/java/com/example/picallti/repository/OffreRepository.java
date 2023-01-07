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


    //Collection<Offre> findByOfferLocate(String date);

    Collection<Offre> findOffreByLocaLDate(String date);

    @Query("SELECT o FROM Offre o ORDER BY o.id DESC ")
    Collection<Offre> findByDateDesc();


    Collection<Offre> findOffreByVille(String ville);

}
