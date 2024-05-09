package com.example.studentapp.dto;

import com.example.studentapp.entity.Student;

public class StudentUtil {

    public static Student convertDtoToEntity(StudentDTO studentDTO) {
       return Student.build(studentDTO.getId(),studentDTO.getName(),
               studentDTO.getDateOfBirth(),studentDTO.getJoiningDate(),studentDTO.getCourse());
    }
}
