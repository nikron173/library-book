package com.nikron.springboot.librarybook.dto;

import com.nikron.springboot.librarybook.entity.Book;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentBookDTO {
    @NotEmpty
    @Size(min = 3, max = 15, message = "Student name range from 3 to 15 character")
    private String name;
    @Email
    private String email;
    private List<Book> books;
}
