package com.itcinfotech.itcinfotech.service;

import com.itcinfotech.itcinfotech.repository.UserDao;
import com.itcinfotech.itcinfotech.entities.UserDto;
import com.itcinfotech.itcinfotech.pojo.User;
import com.itcinfotech.itcinfotech.pojo.UserRequest;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public String createUser(User user) throws ParseException {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setDesignation(user.getDesignation());
        userDto.setManager(user.getManager());
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(user.getDateOfJoining());
        userDto.setDateOfJoining(date);
        userDto.setCreatedBy(user.getCreatedBy());
        userDto.setUpdatedBy(user.getUpdatedBy());
        UserDto userResponse = userDao.save(userDto);
        if (userResponse != null) {
            return "Successfully created.";
        } else
            return "Not Able to Save a record.";

    }

    @Override
    public List<User> getUsers(UserRequest userRequest) {
        List<Sort.Order> orders = new ArrayList<>();
        String[] sortFields = userRequest.getSortingFieldsOrder();
        if (sortFields != null) {
            Arrays.stream(sortFields).forEach(field -> orders.add(new
                    Sort.Order(Sort.Direction.valueOf(field.split("\\.")[1].toUpperCase()), field.split("\\.")[0])));
        }
        Pageable pageable = PageRequest.of(userRequest.getPageNumber(), userRequest.getPageSize(), Sort.by(orders));
        Page<UserDto> userPage = this.userDao.findAll(pageable);
        List<User> userList = new ArrayList<>();
        List<UserDto> userDtoList = userPage.getContent();
        for (UserDto userDto : userDtoList) {
            User users = convertUserFromUserDto(userDto);
            userList.add(users);
        }
        return userList;
    }

    @Override
    public User getUserById(int id) {
        Optional<UserDto> userDto = userDao.findById(id);
        UserDto userDtos = null;
        User user = null;
        if (userDto.isPresent()) {
            userDtos = userDto.get();
            user = convertUserFromUserDto(userDtos);
        }
        return user;
    }

    private User convertUserFromUserDto(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setDesignation(userDto.getDesignation());
        user.setManager(userDto.getManager());
        user.setDateOfJoining(String.valueOf(userDto.getDateOfJoining()));
        user.setCreatedBy(userDto.getCreatedBy());
        user.setUpdatedBy(userDto.getUpdatedBy());
        return user;

    }


}

