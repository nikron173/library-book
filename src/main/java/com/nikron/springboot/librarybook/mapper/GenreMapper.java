package com.nikron.springboot.librarybook.mapper;

import com.nikron.springboot.librarybook.dto.GenreDTO;
import com.nikron.springboot.librarybook.entity.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {
    public GenreDTO genreDTO(Genre genre){
        return new GenreDTO(genre.getId(), genre.getGenreName());
    }

    public Genre dtoToGenre(GenreDTO genreDTO){
        return new Genre(genreDTO.getName());
    }
}
