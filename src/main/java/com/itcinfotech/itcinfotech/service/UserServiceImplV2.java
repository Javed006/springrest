package com.itcinfotech.itcinfotech.service;

import com.itcinfotech.itcinfotech.entities.UserDtoV2;
import com.itcinfotech.itcinfotech.pojo.UserRequest;
import com.itcinfotech.itcinfotech.pojo.UserV2;
import com.itcinfotech.itcinfotech.repository.UserDaoV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImplV2 implements UserServiceV2{
    @Autowired
    UserDaoV2 userDaoV2;
    @Override
    public String createUserV2(UserV2 userV2) throws ParseException {
        UserDtoV2 userDtoV2 = new UserDtoV2();
        userDtoV2.setId(userV2.getId());
        userDtoV2.setName(userV2.getName());
        userDtoV2.setDesignation(userV2.getDesignation());
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(userV2.getDateOfJoining());
        userDtoV2.setDateOfJoining(date);
        userDtoV2.setCreatedBy(userV2.getCreatedBy());
        userDtoV2.setUpdatedBy(userV2.getUpdatedBy());
        UserDtoV2 userResponseV2 = userDaoV2.save(userDtoV2);
        if(userResponseV2!=null)
        {
            return "Successfully created.";
        }
        else
            return "Not Able to Save a record.";
    }

    @Override
    public List<UserV2> getUsersV2(UserRequest userRequest) {
        List<Sort.Order> orders = new ArrayList<>();
        String[] sortFields = userRequest.getSortingFieldsOrder();
        if (sortFields != null) {
            Arrays.stream(sortFields).forEach(field -> orders.add(new
                    Sort.Order(Sort.Direction.valueOf(field.split("\\.")[1].toUpperCase()), field.split("\\.")[0])));
        }
        Pageable pageable = PageRequest.of(userRequest.getPageNumber(), userRequest.getPageSize(), Sort.by(orders));
        Page<UserDtoV2> userPage = this.userDaoV2.findAll(pageable);
        List<UserV2> userList = new ArrayList<>();
        List<UserDtoV2> userDtoList = userPage.getContent();
        for (UserDtoV2 userDtoV2 : userDtoList) {
            UserV2 users = convertUserFromUserDto(userDtoV2);
            userList.add(users);
        }
        return userList;

    }
    @Override
    public UserV2 getUserByIdV2(int id) {
        Optional<UserDtoV2> userDtoV2 = userDaoV2.findById(id);
        UserDtoV2 userDtosV2 = null;
        UserV2 userV2 = null;
        if(userDtoV2.isPresent())
        {
            userDtosV2=userDtoV2.get();
            userV2=convertUserFromUserDto(userDtosV2);
        }
        return userV2;
    }
    public UserV2 convertUserFromUserDto(UserDtoV2 userDtoV2) {
        UserV2 userV2 = new UserV2();
        userV2.setId(userDtoV2.getId());
        userV2.setName(userDtoV2.getName());
        userV2.setDesignation(userDtoV2.getDesignation());
        userV2.setDateOfJoining(String.valueOf(userDtoV2.getDateOfJoining()));
        userV2.setCreatedBy(userDtoV2.getCreatedBy());
        userV2.setUpdatedBy(userDtoV2.getUpdatedBy());
        return userV2;
    }
}
