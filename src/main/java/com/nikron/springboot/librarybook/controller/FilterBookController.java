package com.nikron.springboot.librarybook.controller;

import com.nikron.springboot.librarybook.dto.BookDTO;
import com.nikron.springboot.librarybook.dto.FilterBookDTO;
import com.nikron.springboot.librarybook.entity.Book;
import com.nikron.springboot.librarybook.service.BookService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping(path = "api/v1/books/filter")
@RequiredArgsConstructor
public class FilterBookController {

    @NonNull private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getFilterBooks(@RequestBody FilterBookDTO filterBook){
        return new ResponseEntity<>(bookService.getFilterBooks(filterBook), HttpStatusCode.valueOf(200));
    }
}
