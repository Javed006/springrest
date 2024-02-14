package com.itcinfotech.itcinfotech.service;

import com.itcinfotech.itcinfotech.pojo.Student;
import com.itcinfotech.itcinfotech.pojo.StudentAndCollegeDTO;
import com.itcinfotech.itcinfotech.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
/**
@Service
public class StudentServiceImpl {
    @Autowired
    StudentDao studentDao;

    public List<StudentAndCollegeDTO> getStudentAndCollege()
    {
        return studentDao.findStudentAndCollege();
    }



}**/