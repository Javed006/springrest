package com.itcinfotech.itcinfotech.entities;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "employee")
public class EmployeeDto
{
    @Id
    private int id;
    private String name;
}
