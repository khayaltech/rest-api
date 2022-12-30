package com.example.simpleAPI.controller;

import com.example.simpleAPI.common.GenericResponse;
import com.example.simpleAPI.dto.StudentCreateDTO;
import com.example.simpleAPI.dto.StudentViewDTO;
import com.example.simpleAPI.model.Student;
import com.example.simpleAPI.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping(path = "{id}")
    public ResponseEntity<StudentViewDTO> getStudentById(@PathVariable Long id) {
        final StudentViewDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);

    }


    @GetMapping("/all")
    public ResponseEntity<List<StudentViewDTO>> getStudents() {
        final List<StudentViewDTO> students = studentService.getStudents();
        return ResponseEntity.ok(students);
    }

    @PostMapping("/new")
    public ResponseEntity<?> addNewStudent(@RequestBody StudentCreateDTO studentCreateDTO) {
        studentService.addNewStudent(studentCreateDTO);
        return ResponseEntity.ok(new GenericResponse("Student Created."));
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable("studentId") Long id) {

        studentService.deleteStudent(id);
        return ResponseEntity.ok(new GenericResponse("Student with id -" + id + " deleted..."));

    }

    @PutMapping(path = "{studentId}")
    public ResponseEntity<?> updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "email", required = false) String email
    ) {
        studentService.updateStudent(studentId, name, email);
        return ResponseEntity.ok(new GenericResponse("Student Updated."));
    }
}
