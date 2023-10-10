package com.nikron.springboot.librarybook.service;

import com.nikron.springboot.librarybook.entity.Author;
import com.nikron.springboot.librarybook.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorId(Long authorId) {
        return authorRepository.getAuthorById(authorId)
                .orElseThrow(() -> new IllegalStateException("Author with id "
                        + authorId + " not found"));
    }

//    public Author getAuthorName(String authorName) {
//        return authorRepository.getAuthorByAuthorName(authorName)
//                .orElseThrow(() -> new IllegalStateException("Author with name "
//                        + authorName + " not found"));
//    }

    public void addAuthor(Author author) {
        if (authorRepository.getAuthorByAuthorName(author.getAuthorName())
                .isPresent()){
            throw new IllegalStateException("Author with name " + author.getAuthorName()
                    + " already exists.");
        }
        authorRepository.save(author);
    }

    public void deleteAuthor(Long authorId) {
       if (authorRepository.getAuthorById(authorId).isEmpty()){
           throw new IllegalStateException("Author with id " + authorId
                   + " not found.");
       }
       authorRepository.deleteById(authorId);
    }

//    public void deleteAuthor(Author author) {
//        if (authorRepository.getAuthorByAuthorName(author.getAuthorName()).isEmpty()){
//            throw new IllegalStateException("Author with name " + author.getAuthorName()
//                    + " not found.");
//        }
//        authorRepository.delete(author);
//    }

    @Transactional
    public void updateAuthor(Long authorId, String authorName) {
        Author author = authorRepository.getAuthorById(authorId)
                .orElseThrow(() -> new IllegalStateException("Author with id " + authorId
                        + " not found."));
        if (authorName != null && authorName.length() > 7
        && !Objects.equals(author.getAuthorName(), authorName)){
            author.setAuthorName(authorName);
        }
    }
}
