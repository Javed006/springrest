package com.itcinfotech.itcinfotech.integrationtestcase;

import com.itcinfotech.itcinfotech.entities.UserDto;
import com.itcinfotech.itcinfotech.pojo.User;
import com.itcinfotech.itcinfotech.pojo.UserRequest;
import com.itcinfotech.itcinfotech.repository.UserDao;
import com.itcinfotech.itcinfotech.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserV1MethodsTests {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserDao userDao;
    @Test
    void testCreateUser1() throws ParseException {
        userDao.save(getUserDto());
        assertEquals("Successfully created.",userService.createUser(getUser()));
    }
    @Test
    void getUsers() throws ParseException {
        UserRequest userRequest=new UserRequest();
        userRequest.setPageNumber(1);
        userRequest.setPageSize(5);
        userRequest.setSortingFieldsOrder(new String[]{"name.desc"});
        List<UserDto> userDtoList=new ArrayList<>();
        userDtoList.add(getUserDto());
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new
                Sort.Order(Sort.Direction.valueOf("DESC"), "name"));
        Pageable pageable= PageRequest.of(1,5,Sort.by(orders));
        Page<UserDto> userDtoPage=new PageImpl<>(userDtoList,pageable,10);
        userDao.findAll(pageable);
        assertEquals(13 ,userService.getUsers(userRequest).get(0).getId());
    }
    @Test
    void getUserById() {
        Optional<UserDto> userDto = userDao.findById(12);
        assertEquals(12,userService.getUserById(12).getId());
    }
    public User getUser()
    {
        User user=new User();
        user.setId(5);
        user.setName("akash");
        user.setManager("james");
        user.setCreatedBy("Bond");
        user.setDesignation("Developer");
        user.setDateOfJoining("12/04/2000");
        return user;
    }
    public UserDto getUserDto() throws ParseException {
        UserDto userDto = new UserDto();
        userDto.setId(13);
        userDto.setName("Arun");
        userDto.setManager("Kumar");
        userDto.setCreatedBy("Vijay");
        userDto.setDesignation("Ajith");
        userDto.setUpdatedBy("Suraj");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        userDto.setDateOfJoining(simpleDateFormat.parse("12-04-2002"));
        return userDto;
    }


}
