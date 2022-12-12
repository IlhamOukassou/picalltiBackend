package com.example.picallti.controller;


import com.example.picallti.model.User;
import com.example.picallti.repository.UserRepository;
import com.example.picallti.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users/")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public void addUser(@RequestBody User user){
        userService.addUser(user);
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
    public User getUserById(@RequestParam String email){
        if (userService.getUserByEmail(email).isPresent()){
            return userService.getUserByEmail(email).get();
        }
        else {
            return null;
        }
    }
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public void updateUser(@RequestBody User user){
        userService.update(user);
    }

    @RequestMapping(value = "remove")
    public void updateUser(@RequestParam int id){
        userService.removeUserById(id);
    }



}
