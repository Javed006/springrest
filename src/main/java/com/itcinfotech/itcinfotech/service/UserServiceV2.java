package com.itcinfotech.itcinfotech.service;
import com.itcinfotech.itcinfotech.pojo.UserRequest;
import com.itcinfotech.itcinfotech.pojo.UserV2;

import java.text.ParseException;
import java.util.List;

public interface UserServiceV2 {
    public String createUserV2(UserV2 userV2) throws ParseException;
    List<UserV2> getUsersV2(UserRequest userRequest);
    public UserV2 getUserByIdV2(int id);
}
