package com.example.picallti.repository;

import com.example.picallti.model.Favoris;
import com.example.picallti.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FavorisRepository extends JpaRepository<Favoris,Integer> {

    Optional<List<Favoris>> findByUser(User user);

}
