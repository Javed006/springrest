package com.itcinfotech.itcinfotech.controller;

import com.itcinfotech.itcinfotech.pojo.User;
import com.itcinfotech.itcinfotech.pojo.UserRequest;
import com.itcinfotech.itcinfotech.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "Create User with RequestBody User", notes = "Create User with RequestBody US")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Saved Successfully"),
            @ApiResponse(code = 404, message = "Not saved - The user is not saved")
    })
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
    @ApiOperation(value = "Get all the users", notes = "Return users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieved Successfully"),
            @ApiResponse(code = 404, message = "Not found - The users are not found")
    })
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
    @ApiOperation(value = "Get user by passing an Id", notes = "Return user as per the id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved"),
            @ApiResponse(code = 404, message = "Not found - The user is not found")
    })
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
