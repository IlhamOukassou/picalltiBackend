package com.example.picallti.controller;


import com.example.picallti.model.User;
import com.example.picallti.repository.UserRepository;
import com.example.picallti.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping("/users/")
public class UserController {
    @Autowired
    public UserService userService;


    @RequestMapping(value = "add",method = RequestMethod.POST)
    public void addUser(@RequestBody User user){
       if(userService.userRepository.findByEmail(user.getEmail()).isEmpty()){
           userService.addUser(user);
       }else {
           throw new RuntimeException("email exist");
       }
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

    @RequestMapping(value = "remove")
    public User removeUser(@RequestParam int id){
        if (userService.getUserById(id).isPresent()){
            userService.removeUserById(id);
            return userService.getUserById(id).get();
        }else{
            throw new RuntimeException("user not found");
        }

    }



}
