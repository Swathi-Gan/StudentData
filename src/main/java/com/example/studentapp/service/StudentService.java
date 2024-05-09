package com.example.studentapp.service;

import com.example.studentapp.dto.StudentDTO;
import com.example.studentapp.dto.StudentUtil;
import com.example.studentapp.entity.Student;
import com.example.studentapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    public Student addStudent(StudentDTO studentDto) {
        return studentRepository.save(StudentUtil.convertDtoToEntity(studentDto));
    }

    public Optional<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }

    public List<Student> findStudentByIdOrNameOrCourse(Integer id, String name, String course) {
        return studentRepository.findByIdOrNameOrCourse(id, name, course);
    }

    public Student updateStudent(int id, StudentDTO studentDto) {
        Optional<Student> st = studentRepository.findById(id);
        Student student = null;
        if(st.isPresent()) {
            student = st.get();
            student.setName(studentDto.getName());
            student.setCourse(studentDto.getCourse());
            student.setDateOfBirth(studentDto.getDateOfBirth());
            student.setJoiningDate(studentDto.getJoiningDate());
        }
        return student!= null ? studentRepository.save(student) : null;
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }
}
