package com.itcinfotech.itcinfotech.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcinfotech.itcinfotech.pojo.User;
import com.itcinfotech.itcinfotech.pojo.UserRequest;
import com.itcinfotech.itcinfotech.pojo.UserV2;
import com.itcinfotech.itcinfotech.service.UserServiceImplV2;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserControllerV2.class)
class UserControllerV2Test {
    @Autowired
    MockMvc  mvc;
    @Autowired
    WebApplicationContext webApplicationContext;
    @MockBean
    UserServiceImplV2 userService;
    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void createUser() throws Exception {
        Mockito.when(userService.createUserV2(getUserV2())).thenReturn("Successfully created.");
        MvcResult result = mvc.perform(post("/v2/api/createUser")
                        .content(TestUtil.convertObjectToJsonBytes(getUserV2()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();
        Assertions.assertEquals("Successfully created.",content);
    }

    @Test
    void getAllUsers() throws Exception {
        UserRequest userRequest=new UserRequest();
        userRequest.setPageNumber(0);
        userRequest.setPageSize(1);
        userRequest.setSortingFieldsOrder(new String[]{"Name.desc"});
        List<UserV2> userList=new ArrayList<>();
        userList.add(getUserV2());
        Mockito.when(userService.getUsersV2(userRequest)).thenReturn(userList);
        MvcResult result = mvc.perform(post("/v2/getUsers")
                        .header("Accept", "application/vnd.company.app-v2+json")
                        .content(TestUtil.convertObjectToJsonBytes(userRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();
        Assertions.assertNotNull(content);

    }
    @Test
    void getUserById() throws Exception {
        Mockito.when(userService.getUserByIdV2(12)).thenReturn(getUserV2());
        MvcResult result = mvc.perform(get("/v2/getById").param("version", "2")
                        .param("id","12")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();
        ObjectMapper objectMapper=new ObjectMapper();
        UserV2 user= objectMapper.readValue(content,UserV2.class);
        Assertions.assertEquals(12,user.getId());

    }
    public UserV2 getUserV2(){
        UserV2 userV2=new UserV2();
        userV2.setId(12);
        userV2.setName("xyz");
        userV2.setCreatedBy("abc");
        userV2.setDesignation("Analyst");
        userV2.setUpdatedBy("jvd");
        userV2.setDateOfJoining("12/04/1997");
        return userV2;
    }
}