package com.itcinfotech.itcinfotech.service;
import com.itcinfotech.itcinfotech.pojo.User;
import com.itcinfotech.itcinfotech.pojo.UserRequest;

import java.text.ParseException;
import java.util.List;

public interface UserService {
    public String createUser(User user) throws ParseException;
    List<User> getUsers(UserRequest userRequest);
    public User getUserById(int id);
}
