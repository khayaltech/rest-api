package com.example.simpleAPI.dto;

import com.example.simpleAPI.model.Student;
import lombok.Getter;

import java.io.Serializable;


@Getter
public final class StudentViewDTO implements Serializable {
    private final String name;
    private final String email;
    private final int age;


    private StudentViewDTO(String name, String email, int age) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public static StudentViewDTO of(Student student) {
        return new StudentViewDTO(student.getName(), student.getEmail(), student.getAge());
    }
}
