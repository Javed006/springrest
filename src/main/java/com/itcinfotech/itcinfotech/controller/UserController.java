package com.itcinfotech.itcinfotech.controller;

import com.itcinfotech.itcinfotech.pojo.User;
import com.itcinfotech.itcinfotech.pojo.UserRequest;
import com.itcinfotech.itcinfotech.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @PostMapping("/api/createUser")
    public ResponseEntity<String> saveUser(@RequestBody User user) throws ParseException {
        logger.info("Save User Handler: UserController");
        String user1 = null;
        try{
            user1=this.userService.createUser(user);
            return ResponseEntity.of(Optional.of(user1));
        }catch (Exception exception)
        {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.GONE).build();
        }
    }
    @PostMapping(value = "/getUsers" , produces = "application/vnd.company.app-v1+json")
    public ResponseEntity<List<User>> getAllUsers(@RequestBody UserRequest userRequest)
    {
        logger.info("Get All Users");
        List<User> users = this.userService.getUsers(userRequest);
        if(users.size() < 1)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(users));
    }
    @GetMapping(path = "/getById", params = "version=1")
    public ResponseEntity<User> getUserById(@RequestParam int id)
    {
        logger.info("Get User By Id");
        User userById = this.userService.getUserById(id);
        if(userById==null)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of(userById));
    }
}
