package com.itcinfotech.itcinfotech.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentAndCollegeDTO {
    private String id;
    private String studentName;
    private String cId;
    private String collegeName;
}
