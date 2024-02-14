package com.itcinfotech.itcinfotech.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "college")
@Entity
public class CollegeDto {
    @Id
    @Column(name = "c_id")
    private String cId;
    @Column(name = "college_name")
    private String name;

}
