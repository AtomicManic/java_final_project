package com.expensemanager.controllers;

import com.expensemanager.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.expensemanager.DAO.UserDAO;

@RestController
public class UserController {

    @Autowired
    UserDAO userDao;

    @PostMapping("/user")
    public User addUser(@RequestBody User user){
        return userDao.saveUser(user);
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user){
        return userDao.updateUser(user);
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") int id){
        return userDao.getUserById(id);
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") int id) {return userDao.deleteUserById(id);}
}
