package com.example.simpleAPI.service.impl;

import com.example.simpleAPI.model.Student;
import com.example.simpleAPI.repository.StudentRepository;
import com.example.simpleAPI.service.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void addNewStudent(Student student) {
        Optional<Student> optionalStudent = studentRepository.findStudentByEmail(student.getEmail());
        if (optionalStudent.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);

    }

    @Override
    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);

        if (!exists) {
            throw new IllegalStateException("student with id" + id + "doesn't exist");
        }
        studentRepository.deleteById(id);
    }


    @Transactional
    @Override
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("student with id" + studentId + "doesn't exist"));
        if (name != null && name.length() > 0 && !Objects.equals(name, student.getName())) {
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(email, student.getEmail())) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");

            }
            student.setEmail(email);
       }
        studentRepository.save(student);


    }
}

