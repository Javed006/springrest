package com.itcinfotech.itcinfotech.integrationtestcase;

import com.itcinfotech.itcinfotech.entities.UserDtoV2;
import com.itcinfotech.itcinfotech.pojo.UserRequest;
import com.itcinfotech.itcinfotech.pojo.UserV2;
import com.itcinfotech.itcinfotech.repository.UserDaoV2;
import com.itcinfotech.itcinfotech.service.UserServiceImplV2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserV2MethodsTests {
    @Autowired
    UserServiceImplV2 userService;
    @Autowired
    UserDaoV2 userDao;
    @Test
    void test_CreateUser_v2() throws ParseException {
        userDao.save(getUserDtoV2());
        assertEquals("Successfully created.",userService.createUserV2(getUserV2()));
    }
    @Test
    void get_Users_v2() throws ParseException {
        UserRequest userRequest=new UserRequest();
        userRequest.setPageNumber(1);
        userRequest.setPageSize(5);
        userRequest.setSortingFieldsOrder(new String[]{"name.desc"});
        List<UserDtoV2> userDtoList=new ArrayList<>();
        userDtoList.add(getUserDtoV2());
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new
                Sort.Order(Sort.Direction.valueOf("DESC"), "name"));
        Pageable pageable= PageRequest.of(1,5,Sort.by(orders));
        Page<UserDtoV2> userDtoPage=new PageImpl<>(userDtoList,pageable,10);
        userDao.findAll(pageable);
        assertEquals(3,userService.getUsersV2(userRequest).get(0).getId());
    }
    @Test
    void get_UserById_v2() {
        userDao.findById(3);
        assertEquals(3,userService.getUserByIdV2(3).getId());
    }
    public UserV2 getUserV2()
    {
        UserV2 userV2=new UserV2();
        userV2.setId(5);
        userV2.setName("jayson");
        userV2.setCreatedBy("David");
        userV2.setDesignation("Developer");
        userV2.setDateOfJoining("1/12/2001");
        userV2.setUpdatedBy("jeevan");
        return userV2;
    }
    public UserDtoV2 getUserDtoV2() throws ParseException {
        UserDtoV2 userDtoV2 = new UserDtoV2();
        userDtoV2.setId(13);
        userDtoV2.setName("Arun");
        userDtoV2.setCreatedBy("venkat");
        userDtoV2.setDesignation("Developer");
        userDtoV2.setUpdatedBy("jeevan");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        userDtoV2.setDateOfJoining(simpleDateFormat.parse("12-12-2001"));
        return userDtoV2;
    }


}
