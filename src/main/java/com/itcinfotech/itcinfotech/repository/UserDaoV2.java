package com.itcinfotech.itcinfotech.repository;

import com.itcinfotech.itcinfotech.entities.UserDtoV2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDaoV2 extends JpaRepository<UserDtoV2,Integer> {

}
