package com.nikron.springboot.librarybook.controller;

import com.nikron.springboot.librarybook.dto.GenreDTO;
import com.nikron.springboot.librarybook.error.BaseErrorHandler;
import com.nikron.springboot.librarybook.mapper.GenreMapper;
import com.nikron.springboot.librarybook.service.GenreService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/genre")
@AllArgsConstructor
public class GenreController {
    @NonNull private final GenreService genreService;
    @NonNull private final GenreMapper genreMapper;

    @GetMapping
    @ResponseBody
    public List<GenreDTO> getAllGenres(){
        return genreService.getAllGenres()
                .stream().map(genreMapper::genreDTO).toList();
    }

    @GetMapping(path = "{id}")
    public GenreDTO getGenreId(@PathVariable(name = "id") UUID id){
        return genreMapper.genreDTO(genreService.getGenreId(id));
    }

    @PostMapping
    public ResponseEntity<String> addGenre(@RequestBody @Valid GenreDTO genre) throws BaseErrorHandler {
        genreService.addGenre(genreMapper.dtoToGenre(genre));
        return new ResponseEntity<>("Genre created.", HttpStatusCode.valueOf(201));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteGenre(@PathVariable(name = "id") UUID id) throws BaseErrorHandler {
        genreService.deleteGenre(id);
        return new ResponseEntity<>(String.format("Genre id: %s deleted.", id),
                HttpStatusCode.valueOf(200));
    }


    @PutMapping(path = "{id}")
    public ResponseEntity<String> updateGenre(@PathVariable(name = "id") UUID id,
                             @RequestBody @Valid GenreDTO genre) throws BaseErrorHandler {
        genreService.updateGenre(id, genreMapper.dtoToGenre(genre));
        return new ResponseEntity<>(String.format("Genre id: %s updated.", id),
                HttpStatusCode.valueOf(200));
    }
}
