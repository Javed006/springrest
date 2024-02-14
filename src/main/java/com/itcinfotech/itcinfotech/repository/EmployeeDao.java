package com.itcinfotech.itcinfotech.repository;

import com.itcinfotech.itcinfotech.entities.EmployeeDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<EmployeeDto,Integer> {
}
