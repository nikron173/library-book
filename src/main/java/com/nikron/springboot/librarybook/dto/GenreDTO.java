package com.nikron.springboot.librarybook.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreDTO {
    private UUID id;
    @Size(min = 3, max = 20, message = "Genre name range from 3 to 20 character")
    @NonNull
    private String name;
}
