package com.itcinfotech.itcinfotech.repository;

import com.itcinfotech.itcinfotech.entities.StudentDto;
import com.itcinfotech.itcinfotech.pojo.StudentAndCollegeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface StudentDao extends JpaRepository<StudentDto,String> {
    @Query(value = "SELECT new com.itcinfotech.itcinfotech.pojo.StudentAndCollegeDTO(s.id, s.studentName, c.cId, c.name) " +
            "  from StudentDto s join CollegeDto c ON s.cId = c.cId ")
    Page<StudentAndCollegeDTO> findStudentAndCollege(Pageable pageable);


}