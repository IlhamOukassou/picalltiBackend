package com.example.picallti.service;


import com.example.picallti.dto.LoginRequestDTO;
import com.example.picallti.exception.LoginRequestException;
import com.example.picallti.model.User;
import com.example.picallti.repository.UserRepository;
import com.example.picallti.util.ImageDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    public void addUser(User user) {
        Optional<User> userByEmail = userRepository
                .findByEmail(user.getEmail());
        Optional<User> userByPhone = userRepository
                .findByPhone(user.getPhone());
        if(userByEmail.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email already exists !!");
        }else if(userByPhone.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Phone Number is already taken !!");
        }else {
            String passwordEncoded = bcrypt.encode(user.getPassword());
            user.setPassword(passwordEncoded);
            userRepository.save(user);
        }

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

    public Optional<User> getUserByPhone (int phone){
        return userRepository.findByPhone(phone);
    }

    public byte[] downloadImage(byte[] imageData){
        byte[] images = ImageDataUtil.decompressImage(imageData);
        return images;
    }
    public void update(User user){
        User userFromDb = userRepository.findById(user.getId()).get();
        if (userFromDb != null){
            userFromDb.setPrenom(user.getPrenom());
            userFromDb.setNom(user.getNom());
            userFromDb.setGenre(user.getGenre());
            userFromDb.setEmail(user.getEmail());
            String passwordEncoded = bcrypt.encode(userFromDb.getPassword());
            userFromDb.setPassword(passwordEncoded);
            userFromDb.setPhone(user.getPhone());
            userFromDb.setBio(user.getBio());
            userFromDb.setRole(user.getRole());
            userRepository.save(userFromDb);
        }
    }

    public void updateWithImage(User user,MultipartFile file ) throws IOException {
        User userFromDb = userRepository.findById(user.getId()).get();
        if (userFromDb != null){
            userFromDb.setPrenom(user.getPrenom());
            userFromDb.setNom(user.getNom());
            userFromDb.setGenre(user.getGenre());
            userFromDb.setEmail(user.getEmail());
            String passwordEncoded = bcrypt.encode(userFromDb.getPassword());
            userFromDb.setPassword(passwordEncoded);
            userFromDb.setPhone(user.getPhone());
            userFromDb.setImageData(ImageDataUtil.compressImage(file.getBytes()));
            userFromDb.setBio(user.getBio());
            userFromDb.setRole(user.getRole());
            userRepository.save(userFromDb);
        }
    }
    public void removeUserById(int id){
        userRepository.deleteById(id);
    }

    public Optional<User> loginUser(LoginRequestDTO loginRequestDTO){
        Optional<User> opUser = userRepository.findByEmail(loginRequestDTO.getEmail());
        if(opUser.isPresent()){
            User dbUser = opUser.get(); 
            if(bcrypt.matches(loginRequestDTO.getPassword(),dbUser.getPassword())){
                return opUser; 
            }else throw new LoginRequestException("Password is wrong");
        }else throw new LoginRequestException("Email is wrong !"); 

    }


}











 /* public void UpdateUserWithImage( MultipartFile file, User user) throws IOException {
        Optional<User> userByEmail = userRepository
                .findByEmail(user.getEmail());
        Optional<User> userByPhone = userRepository
                .findByPhone(user.getPhone());
            String passwordEncoded = bcrypt.encode(user.getPassword());
            user.setPassword(passwordEncoded);
            user.setImageData(ImageDataUtil.compressImage(file.getBytes()));
            userRepository.save(user);


    }*/
