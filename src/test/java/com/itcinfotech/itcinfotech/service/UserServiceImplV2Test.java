package com.itcinfotech.itcinfotech.service;

import com.itcinfotech.itcinfotech.entities.UserDto;
import com.itcinfotech.itcinfotech.entities.UserDtoV2;
import com.itcinfotech.itcinfotech.pojo.User;
import com.itcinfotech.itcinfotech.pojo.UserRequest;
import com.itcinfotech.itcinfotech.pojo.UserV2;
import com.itcinfotech.itcinfotech.repository.UserDao;
import com.itcinfotech.itcinfotech.repository.UserDaoV2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserServiceImplV2Test {
    @Mock
    UserDaoV2 userDaoV2;
    @InjectMocks
    UserServiceImplV2 userServiceImplV2;

    @Test
    void test_for_null_create_User_v2() throws Exception {
        assertEquals("Not Able to Save a record.", userServiceImplV2.createUserV2(getUserV2()));
    }
    @Test
    void testCreateUser1() throws ParseException {
        Mockito.when(userDaoV2.save(Mockito.any())).thenReturn(getUserDtoV2());
        assertEquals("Successfully created.",userServiceImplV2.createUserV2(getUserV2()));
    }
    @Test
    void getUsers_v2() {
        UserRequest userRequest=new UserRequest();
        userRequest.setPageNumber(1);
        userRequest.setPageSize(5);
        userRequest.setSortingFieldsOrder(new String[]{"designation.desc"});
        List<UserDtoV2> userDtoV2List=new ArrayList<>();
        userDtoV2List.add(getUserDtoV2());
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new
                Sort.Order(Sort.Direction.valueOf("DESC"), "designation"));
        Pageable pageable= PageRequest.of(1,5,Sort.by(orders));
        Page<UserDtoV2> userDtoPage=new PageImpl<>(userDtoV2List,pageable,10);
        Mockito.when(userDaoV2.findAll(pageable)).thenReturn(userDtoPage);
        assertEquals(1,userServiceImplV2.getUsersV2(userRequest).get(0).getId());
    }

    @Test
    void getUserById_v2() {
        Mockito.when(userDaoV2.findById(Mockito.any())).thenReturn(Optional.of(getUserDtoV2()));
        assertEquals(12,userServiceImplV2.getUserByIdV2(0).getId());
    }

    public UserDtoV2 getUserDtoV2(){
        UserDtoV2 userDtoV2 = new UserDtoV2();
        userDtoV2.setId(12);
        userDtoV2.setName("Vijay");
        userDtoV2.setCreatedBy("Kumar");
        userDtoV2.setDesignation("Developer");
        userDtoV2.setUpdatedBy("abc");
        return userDtoV2;
    }

    public UserV2 getUserV2(){
        UserV2 userV2=new UserV2();
        userV2.setId(2);
        userV2.setName("Dev");
        userV2.setCreatedBy("Raj");
        userV2.setDesignation("Developer");
        userV2.setDateOfJoining("22/07/2022");
        return userV2;
    }


}