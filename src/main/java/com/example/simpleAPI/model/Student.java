package com.example.simpleAPI.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Data
@Entity
@Table(name="students")
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;

    @Transient
    private int age;

    public Student(String name, String email, LocalDate dob, int age) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.age = age;
    }



    public int getAge()
    {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }


}
