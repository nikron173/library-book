package com.nikron.springboot.librarybook.controller;

import com.nikron.springboot.librarybook.dto.AuthorDTO;
import com.nikron.springboot.librarybook.error.BaseErrorHandler;
import com.nikron.springboot.librarybook.mapper.AuthorMapper;
import com.nikron.springboot.librarybook.service.AuthorService;
import com.nikron.springboot.librarybook.entity.Author;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/author")
@AllArgsConstructor
public class AuthorController {
    @NonNull private final AuthorService authorService;
    @NonNull private final AuthorMapper authorMapper;

    @GetMapping
    @ResponseBody
    public List<AuthorDTO> getAllAuthors(){
        return authorService.getAllAuthors()
                .stream().map(authorMapper::authorDTO).toList();
    }

    @GetMapping(path = "{id}")
    public Author getAuthorId(@PathVariable(name = "id") UUID id){
        return authorService.getAuthorId(id);
    }

    @PostMapping
    public ResponseEntity<String> addAuthor(@Valid @RequestBody AuthorDTO author,
                                            BindingResult bindingResult) throws BaseErrorHandler {
        if (bindingResult.hasErrors()){
            throw  new BaseErrorHandler(Objects.requireNonNull(
                    bindingResult.getFieldError()).getDefaultMessage());
        }
        authorService.addAuthor(authorMapper.dtoToAuthor(author));
        return new ResponseEntity<>("Author created.", HttpStatusCode.valueOf(201));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteAuthor(
            @PathVariable(name = "id") UUID id) throws BaseErrorHandler {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(String.format("Author id: %s deleted.", id),
                HttpStatusCode.valueOf(200));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<String> updateAuthor(@PathVariable(name = "id") UUID id,
                             @RequestBody @Valid AuthorDTO author) throws BaseErrorHandler {
        authorService.updateAuthor(id, authorMapper.dtoToAuthor(author));
        return new ResponseEntity<>(String.format("Author id: %s updated.", id),
                HttpStatusCode.valueOf(200));
    }
}
