package com.itcinfotech.itcinfotech.controller;
import com.itcinfotech.itcinfotech.pojo.UserRequest;
import com.itcinfotech.itcinfotech.pojo.UserV2;
import com.itcinfotech.itcinfotech.service.UserServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v2")
public class UserControllerV2 {
    @Autowired
    UserServiceV2 userServiceV2;

    @PostMapping("/api/createUser")
     public ResponseEntity<String> create(@RequestBody UserV2 userV2) throws ParseException {
        String user = null;
        try{
            user=this.userServiceV2.createUserV2(userV2);
            return ResponseEntity.of(Optional.of(user));
        }catch (Exception exception)
        {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.GONE).build();
        }
    }
    @PostMapping(value = "/getUsers" , produces = "application/vnd.company.app-v2+json")
    public ResponseEntity<List<UserV2>> getAll(@RequestBody UserRequest userRequest){
        List<UserV2> users = this.userServiceV2.getUsersV2(userRequest);
        if(users.size() < 1)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(users));
    }
    
    @GetMapping(value = "/getById", params = "version=2")
    public ResponseEntity<UserV2> getById(@RequestParam int id)
    {
        UserV2 userById = this.userServiceV2.getUserByIdV2(id);
        if(userById==null)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of(userById));
    }
}
