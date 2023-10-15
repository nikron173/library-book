package com.nikron.springboot.librarybook.mapper;

import com.nikron.springboot.librarybook.dto.AuthorDTO;
import com.nikron.springboot.librarybook.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {
    public AuthorDTO authorDTO(Author author){
        return new AuthorDTO(author.getId(),author.getAuthorName());
    }

    public Author dtoToAuthor(AuthorDTO authorDTO){
        return new Author(authorDTO.getName());
    }
}
