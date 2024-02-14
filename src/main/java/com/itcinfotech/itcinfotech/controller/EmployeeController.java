package com.itcinfotech.itcinfotech.controller;

import com.itcinfotech.itcinfotech.pojo.Employee;
import com.itcinfotech.itcinfotech.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiOperation("EmployeeTransactionalManager")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/add")
    @ApiOperation("CreateEmployee")
    public ResponseEntity<String> saveEmployee(@RequestBody Employee employee) throws Exception{
        return new ResponseEntity<String>(this.employeeService.addEmployee(employee), HttpStatus.CREATED);
    }
}