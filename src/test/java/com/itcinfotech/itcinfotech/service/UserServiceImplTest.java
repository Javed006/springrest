package com.itcinfotech.itcinfotech.service;

import com.itcinfotech.itcinfotech.entities.UserDto;
import com.itcinfotech.itcinfotech.pojo.User;
import com.itcinfotech.itcinfotech.pojo.UserRequest;
import com.itcinfotech.itcinfotech.repository.UserDao;
import jdk.dynalink.beans.StaticClass;
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
class UserServiceImplTest {
    @Mock
    UserDao userDao;
    @InjectMocks
    UserServiceImpl userService;

    @Test
    void test_for_null_create_User() throws Exception {
        assertEquals("Not Able to Save a record.", userService.createUser(getUser()));
    }

    @Test
    void testCreateUser1() throws ParseException {
        Mockito.when(userDao.save(Mockito.any())).thenReturn(getUserDto());
        assertEquals("Successfully created.", userService.createUser(getUser()));
    }

    @Test
    void getUsers() {
        UserRequest userRequest = new UserRequest();
        userRequest.setPageNumber(1);
        userRequest.setPageSize(5);
        userRequest.setSortingFieldsOrder(new String[]{"Name.desc"});
        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(getUserDto());
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new
                Sort.Order(Sort.Direction.valueOf("DESC"), "Name"));
        Pageable pageable = PageRequest.of(1, 5, Sort.by(orders));
        Page<UserDto> userDtoPage = new PageImpl<>(userDtoList, pageable, 10);
        Mockito.when(userDao.findAll(pageable)).thenReturn(userDtoPage);
        assertEquals(1, userService.getUsers(userRequest).get(0).getId());
    }

    @Test
    void getUserById() {
        Mockito.when(userDao.findById(Mockito.any())).thenReturn(Optional.of(getUserDto()));
        assertEquals(12, userService.getUserById(0).getId());
    }

    public UserDto getUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId(12);
        userDto.setName("xyz");
        userDto.setManager("yzs");
        userDto.setCreatedBy("abc");
        userDto.setDesignation("argv");
        userDto.setUpdatedBy("adequacy");
        return userDto;
    }

    public User getUser() {
        User user = new User();
        user.setId(1);
        user.setName("xyz");
        user.setManager("yzs");
        user.setCreatedBy("abc");
        user.setDesignation("agjgc");
        user.setDateOfJoining("12/04/2000");
        return user;
    }

}