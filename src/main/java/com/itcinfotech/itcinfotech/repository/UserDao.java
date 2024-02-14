package com.itcinfotech.itcinfotech.repository;

import com.itcinfotech.itcinfotech.entities.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserDao extends JpaRepository<UserDto,Integer>
{

}
