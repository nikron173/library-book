package com.nikron.springboot.librarybook.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorDTO {
    private UUID id;
    @NotEmpty
    @Size(min = 7, max = 20, message = "Author name range from 7 to 20 character")
    private String name;
}
