package com.example.picallti.service;


import com.example.picallti.dto.LoginRequestDTO;
import com.example.picallti.exception.LoginRequestException;
import com.example.picallti.model.User;
import com.example.picallti.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public void addUser(User user){
        userRepository.save(user);
    }

    public Collection<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int id){
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public void update(User user){
        User userFromDb = userRepository.findById(user.getId()).get();
        if (userFromDb != null){
            userFromDb.setPrenom(user.getPrenom());
            userFromDb.setNom(user.getNom());
            userFromDb.setGenre(user.getGenre());
            userFromDb.setEmail(user.getEmail());
            userFromDb.setPassword(user.getPassword());
            userFromDb.setPhone(user.getPhone());
            userFromDb.setPhoto(user.getPhoto());
            userFromDb.setBio(user.getBio());
            userFromDb.setRole(user.getRole());
            userRepository.save(userFromDb);
        }
    }

    public void removeUserById(int id){
        userRepository.deleteById(id);
    }

    public Optional<User> loginUser(LoginRequestDTO loginRequestDTO){
        return Optional.ofNullable(userRepository.findOneByEmailIgnoreCaseAndPassword(loginRequestDTO.getEmail(), loginRequestDTO.getPassword())
                .orElseThrow(() -> new LoginRequestException("Login Error !")));
    }


}
