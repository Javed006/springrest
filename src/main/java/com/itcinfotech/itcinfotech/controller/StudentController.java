package com.itcinfotech.itcinfotech.controller;

import com.itcinfotech.itcinfotech.pojo.StudentAndCollegeDTO;
import com.itcinfotech.itcinfotech.pojo.UserRequest;
import com.itcinfotech.itcinfotech.repository.StudentDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
@Slf4j
public class StudentController {
    @Autowired
    StudentDao studentDao;
    @PostMapping
    public ResponseEntity<List<StudentAndCollegeDTO>> getStudents(@RequestBody UserRequest userRequest)
    {
        List<Sort.Order> orders = new ArrayList<>();
        String[] sortFields = userRequest.getSortingFieldsOrder();
        if (sortFields != null) {
            Arrays.stream(sortFields).forEach(field -> orders.add(new
                    Sort.Order(Sort.Direction.valueOf(field.split("\\.")[1].toUpperCase()), field.split("\\.")[0])));
        }
        log.info("Student info");
        Pageable pageable = PageRequest.of(userRequest.getPageNumber(), userRequest.getPageSize(), Sort.by(orders));
        Page<StudentAndCollegeDTO> page = studentDao.findStudentAndCollege(pageable);
        List<StudentAndCollegeDTO> list = page.getContent();
        if(list.isEmpty()){
          //  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.of(Optional.of(list));
    }

}
