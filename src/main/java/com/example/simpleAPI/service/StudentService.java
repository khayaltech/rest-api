package com.example.simpleAPI.service;

import com.example.simpleAPI.dto.StudentCreateDTO;
import com.example.simpleAPI.dto.StudentViewDTO;
import com.example.simpleAPI.model.Student;
import java.util.List;


public interface StudentService {
    List<StudentViewDTO> getStudents();

    StudentViewDTO addNewStudent(StudentCreateDTO studentCreateDTO);

    void deleteStudent(Long id);

    void updateStudent(Long studentId, String name, String email);

    StudentViewDTO getStudentById(Long id);
}
