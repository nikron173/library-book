package com.nikron.springboot.librarybook.controller;

import com.nikron.springboot.librarybook.dto.BookCreateDTO;
import com.nikron.springboot.librarybook.error.BaseErrorHandler;
import com.nikron.springboot.librarybook.mapper.BookMapper;
import com.nikron.springboot.librarybook.service.BookService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/book")
@AllArgsConstructor
public class BookController {
    @NonNull private final BookService bookService;
    @NonNull private final BookMapper bookMapper;

    @GetMapping
    @ResponseBody
    public List<BookCreateDTO> getBooks(){
        return bookService.getBooks().stream()
                .map(bookMapper::bookCreateDTO).toList();
    }

    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody BookCreateDTO book) throws BaseErrorHandler {
        UUID id = bookService.addBook(bookMapper.dtoToBook(book));
        return new ResponseEntity<>(String.format("Student id: %s updated.", id),
                HttpStatusCode.valueOf(200));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable(name = "id") UUID id) throws BaseErrorHandler {
        bookService.deleteBook(id);
        return new ResponseEntity<>(String.format("Book id: %s updated.", id),
                HttpStatusCode.valueOf(200));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<String> updateBook(@PathVariable(name = "id") UUID id,
                                     @RequestBody BookCreateDTO book) throws BaseErrorHandler {
        bookService.updateBook(id, bookMapper.dtoToBook(book));
        return new ResponseEntity<>(String.format("Book id: %s updated.", id),
                HttpStatusCode.valueOf(201));
    }

}
