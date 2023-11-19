package com.nikron.springboot.librarybook.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class StudentInfoDTO {
    private UUID id;
    @Size(min = 7, max = 20, message = "Author name range from 7 to 20 character")
    private String name;
    @Email
    private String email;
    private LocalDate birthDay;
    @Size(min = 3, max = 150, message = "Age range from 3 to 150")
    private Integer age;
}
