package com.nikron.springboot.librarybook.dto;

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
public class BookCreateDTO {
    private UUID id;
    @NonNull
    @Size(min = 3, max = 20, message = "Book name range from 7 to 20 character")
    private String bookName;
    @NonNull private AuthorDTO author;
    @NonNull private GenreDTO genre;
    @NonNull
    private LocalDate createDate;
}
