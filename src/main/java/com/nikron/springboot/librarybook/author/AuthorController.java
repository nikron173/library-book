package com.nikron.springboot.librarybook.author;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @GetMapping(path = "{authorId}")
    public Author getAuthorId(@PathVariable(name = "authorId") Long authorId){
        return authorService.getAuthorId(authorId);
    }

//    @GetMapping(path = "{authorName}")
//    public Author getAuthorName(@PathVariable(name = "authorName") String authorName){
//        return authorService.getAuthorName(authorName);
//    }

    @PostMapping
    public void addAuthor(@RequestBody Author author){
        authorService.addAuthor(author);
    }

    @DeleteMapping(path = "{authorId}")
    public void deleteAuthor(@PathVariable(name = "authorId") Long authorId){
        authorService.deleteAuthor(authorId);
    }

//    @DeleteMapping
//    public void deleteAuthor(@RequestBody Author author){
//        authorService.deleteAuthor(author);
//    }

    @PutMapping(path = "{authorId}")
    public void updateAuthor(@PathVariable(name = "authorId") Long authorId,
                             @RequestParam(name = "authorName") String authorName){
        authorService.updateAuthor(authorId, authorName);
    }
}
