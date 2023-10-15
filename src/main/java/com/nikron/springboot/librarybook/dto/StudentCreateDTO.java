package com.nikron.springboot.librarybook.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class StudentCreateDTO {
    private UUID id;
    @NotEmpty
    @NonNull
    @Size(min = 3, max = 15, message = "Student name range from 3 to 15 character")
    private String name;
    @Email
    @NonNull
    private String email;
    @NonNull
    private LocalDate birthDay;
}
