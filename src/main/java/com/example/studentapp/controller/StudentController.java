package com.example.studentapp.controller;

import com.example.studentapp.dto.StudentDTO;
import com.example.studentapp.entity.Student;
import com.example.studentapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    public StudentController() {
        System.out.println("Student controller called");
    }

    @Autowired
    StudentService studentService;
    @GetMapping("/byIdNameOrCourse")
    public ResponseEntity<List<Student>> getStudentDetailsByIdOrNameOrCourse(@RequestParam(value = "id",required = false) Integer id,
                                                                             @RequestParam(value = "name",required = false) String name,
                                                                             @RequestParam(value = "course",required = false) String course) throws Exception {
        List<Student> student = null;
        if(id != null || name != null || course != null) {
             student = Optional.of(studentService.findStudentByIdOrNameOrCourse(id, name, course)).
                     orElseThrow(() -> new Exception("Student not found error"));
        } else {
            student = Optional.of(studentService.getAll()).orElseThrow(() -> new Exception("Error during retrieval of data"));
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping("/saveStudent")
    public ResponseEntity<Student> saveStudentData(@RequestBody StudentDTO studentDTO) {
        Student student = studentService.addStudent(studentDTO);
        return student!=null ? ResponseEntity.ok(student) : new ResponseEntity<Student>(student,HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudentData(@PathVariable int id, @RequestBody StudentDTO studentDTO) {
        Student student = studentService.updateStudent(id, studentDTO);
        return student!=null ? ResponseEntity.ok(student) : new ResponseEntity<Student>(student,HttpStatus.BAD_REQUEST);
    }
}
