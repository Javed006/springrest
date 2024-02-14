package com.itcinfotech.itcinfotech.service;

import com.itcinfotech.itcinfotech.entities.AddressDto;
import com.itcinfotech.itcinfotech.entities.EmployeeDto;
import com.itcinfotech.itcinfotech.pojo.Address;
import com.itcinfotech.itcinfotech.pojo.Employee;
import com.itcinfotech.itcinfotech.repository.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class EmployeeService {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    AddressService addressService;
    @Transactional
    public String addEmployee(Employee employee) throws Exception {
        EmployeeDto employeeDto=new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        EmployeeDto employeeSavedToDB = this.employeeDao.save(employeeDto);

        AddressDto addressDto = new AddressDto();
        addressDto.setId(employee.getAddress().getId());
        addressDto.setAddress(employee.getAddress().getAddress());
        addressDto.setEmployeeDto(employeeSavedToDB);

        this.addressService.addAddress(addressDto);
        return "Saved Successfully";
    }

}
