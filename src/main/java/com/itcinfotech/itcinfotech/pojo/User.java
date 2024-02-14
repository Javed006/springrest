package com.itcinfotech.itcinfotech.pojo;

import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String designation;
    private String manager;
    private String dateOfJoining;
    private String updatedBy;
    private String createdBy;
}
