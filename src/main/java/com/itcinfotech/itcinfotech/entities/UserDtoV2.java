package com.itcinfotech.itcinfotech.entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user_v2")
public class UserDtoV2 {
    @Id
    @Column(name = "user_id",nullable = false)
    @GeneratedValue
    private int id;
    @Column(name = "user_name")
    private String name;
    private String designation;
    @Column(name = "date_of_joining")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateOfJoining;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "created_by")
    private String createdBy;

}

