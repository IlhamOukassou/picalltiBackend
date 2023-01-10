package com.example.picallti.controller;


import com.example.picallti.dto.LoginRequestDTO;
import com.example.picallti.model.User;
import com.example.picallti.repository.UserRepository;
import com.example.picallti.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/users/")
public class UserController {
    @Autowired
    public UserService userService;
    @Autowired
    private UserRepository userRepository;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public void addUser(@RequestBody User user){
           userService.addUser(user);

    }



    // Setting the pp of the user :
    @GetMapping(value = "downloadImage")
    public ResponseEntity<?> downloadImage(@RequestParam int id){
        Optional<User> userdb = userService.getUserById(id);
        if (userdb.isPresent()){
            byte[] imageData = userService.downloadImage(userdb.get().getImageData());
            return  ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("image/png"))
                    .body(imageData);
        }
        return null;
    }


    @RequestMapping(value = "getAll")
    public Collection<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @RequestMapping(value = "getById")
    public User getUserById(@RequestParam int id){
        if (userService.getUserById(id).isPresent()){
            return userService.getUserById(id).get();
        }
        else {
            return null;
        }
    }


    @RequestMapping(value = "getByEmail")
    public User getUserByEmail(@RequestParam String email){
        if (userService.getUserByEmail(email).isPresent()){
            return userService.getUserByEmail(email).get();
        }
        else {
            return null;
        }
    }
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public User updateUser(@RequestBody User user){
        if (userService.getUserById(user.getId()).isPresent()){
            userService.update(user);
            return user;
        }else{
            throw new RuntimeException("user not found");
        }
    }

    // Changing the profile image :
    @PostMapping(value = "updateWithImage/{id}")
    public User updateUserWithImage(@PathVariable Integer id,
                                    @RequestParam("image")MultipartFile file ) throws IOException {

        if (userService.getUserById(id).isPresent()){
            User userdb = userService.getUserById(id).get();
            userService.updateWithImage(userdb, file);
            return userdb;
        }else{
            throw new RuntimeException("user not found");
        }
    }


    @DeleteMapping(value = "/remove/{id}")
    public void deleteUserById(@PathVariable Integer id) {
        if (userService.getUserById(id).isPresent()){
            userService.removeUserById(id);
            System.out.println(HttpStatus.OK);}
        else {
            System.out.println(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "existsByEmail")
    public boolean existsUserByEmail(@RequestParam String email){
        if (userService.getUserByEmail(email).isPresent()){
            return true;
        }else return false;
    }

    @RequestMapping(value = "existsByPhone")
    public boolean existsUserByPhone(@RequestParam int phone ){
        if (userService.getUserByPhone(phone).isPresent()){
            return true;
        }else return false;
    }

    @PostMapping("/login")
    public ResponseEntity<Optional<User>> loginUser(@RequestBody LoginRequestDTO loginRequestDTO){
        Optional<User> user = userService.loginUser(loginRequestDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }





}
