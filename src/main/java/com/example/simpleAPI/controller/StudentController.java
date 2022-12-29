package com.example.simpleAPI.controller;

import com.example.simpleAPI.model.Student;
import com.example.simpleAPI.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;


    @GetMapping("/")
    List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping("/add")
    public void addNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id) {

        studentService.deleteStudent(id);

    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(name="name",required = false) String name,
            @RequestParam(name="email",required = false) String email
    ){
        studentService.updateStudent(studentId,name,email);
    }
}
