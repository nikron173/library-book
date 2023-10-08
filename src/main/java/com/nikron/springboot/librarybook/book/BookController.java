package com.nikron.springboot.librarybook.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks(
            @RequestParam(required = false, name = "authorName") String authorName,
            @RequestParam(required = false, name = "genreName") String genreName,
            @RequestParam(required = false, name = "bookName") String bookName,
            @RequestParam(required = false, name = "studentName") String studentName){
        System.out.println(authorName + " " + genreName + " " + bookName + " " + studentName);
        return bookService.getBooks(
                authorName != null ? URLDecoder.decode(authorName, StandardCharsets.UTF_8) : null,
                genreName != null ? URLDecoder.decode(genreName, StandardCharsets.UTF_8) : null,
                bookName != null ? URLDecoder.decode(bookName, StandardCharsets.UTF_8) : null,
                studentName != null ? URLDecoder.decode(studentName, StandardCharsets.UTF_8) : null
        );
    }

    @GetMapping(path = "{bookId}")
    public Book getBookId(@PathVariable(name = "bookId") Long bookId){
        return bookService.getBookId(bookId);
    }


    @PostMapping
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }

    @DeleteMapping(path = "{bookId}")
    public void deleteAuthor(@PathVariable(name = "bookId") Long bookId){
        bookService.deleteBook(bookId);
    }


    @PutMapping(path = "{bookId}")
    public void updateBook(@PathVariable(name = "bookId") Long bookId,
                           @RequestParam(name = "bookName", required = false) String bookName,
                           @RequestParam(name = "authorName", required = false) String authorName,
                           @RequestParam(name = "createDate", required = false) Integer createDate,
                           @RequestParam(name = "genreName", required = false) String genreName,
                           @RequestParam(name = "studentName", required = false) String studentName){
        bookService.updateBook(bookId, authorName, genreName, bookName, createDate, studentName);
    }

}
