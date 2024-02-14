package com.itcinfotech.itcinfotech.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcinfotech.itcinfotech.pojo.User;
import com.itcinfotech.itcinfotech.pojo.UserRequest;
import com.itcinfotech.itcinfotech.service.UserServiceImpl;
import com.itcinfotech.itcinfotech.utils.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;
    @MockBean
    UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }
    @Test
    public void create_user() throws Exception {
        Mockito.when(userService.createUser(getUser())).thenReturn("Successfully created.");
        MvcResult result = mvc.perform(post("/v1/api/createUser").content(TestUtil.convertObjectToJsonBytes(getUser()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();
        Assertions.assertEquals("Successfully created.",content);

    }

    @Test
    void getAllUsers() throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setPageNumber(0);
        userRequest.setPageSize(1);
        userRequest.setSortingFieldsOrder(new String[]{"name.desc"});
        List<User> userList = new ArrayList<>();
        userList.add(getUser());
        Mockito.when(userService.getUsers(userRequest)).thenReturn(userList);
        MvcResult result = mvc.perform(post("/v1/getUsers")
                        .header("Accept", "application/vnd.company.app-v1+json")
                        .content(TestUtil.convertObjectToJsonBytes(userRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();
        Assertions.assertNotNull(content);
    }

    @Test
    void getUserById() throws Exception {
        Mockito.when(userService.getUserById(12)).thenReturn(getUser());
        MvcResult result = mvc.perform(get("/v1/getById").param("version", "1")
                        .param("id", "12")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(content, User.class);
        Assertions.assertEquals(12, user.getId());
    }

    public User getUser() {
        User user = new User();
        user.setId(12);
        user.setName("xyz");
        user.setManager("yzs");
        user.setCreatedBy("abc");
        user.setDesignation("argv");
        user.setUpdatedBy("jvd");
        user.setDateOfJoining("12/04/2000");
        return user;
    }
}