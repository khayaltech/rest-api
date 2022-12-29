package com.example.simpleAPI.dto;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
public class StudentCreateDTO {

    private String name;
    private String email;
    private LocalDate dob;

}