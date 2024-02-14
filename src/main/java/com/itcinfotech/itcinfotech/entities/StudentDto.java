package com.itcinfotech.itcinfotech.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "student")
@Entity
public class StudentDto {
    @Id
    private String id;
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "c_id")
    private String cId;
}
