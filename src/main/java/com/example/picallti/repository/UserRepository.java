package com.example.picallti.repository;

import com.example.picallti.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);
<<<<<<< HEAD
=======
    Boolean existsUserByEmail(String email);




>>>>>>> ada8795bcc4d346f75a394bcc11d3896f325e593
}
