package com.nikron.springboot.librarybook.service;

import com.nikron.springboot.librarybook.entity.Author;
import com.nikron.springboot.librarybook.error.BaseErrorHandler;
import com.nikron.springboot.librarybook.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {
    @NonNull private final AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorId(UUID id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Author with id "
                        + id + " not found"));
    }

    public void addAuthor(Author author) throws BaseErrorHandler {
        if (authorRepository.getAuthorByAuthorName(author.getAuthorName())
                .isPresent()){
            throw new BaseErrorHandler("Author with name " + author.getAuthorName()
                    + " already exists.");
        }
        authorRepository.save(author);
    }

    public void deleteAuthor(UUID id) throws BaseErrorHandler {
       if (authorRepository.findById(id).isEmpty()){
           throw new BaseErrorHandler("Author with id " + id
                   + " not found.");
       }
       authorRepository.deleteById(id);
    }

    @Transactional
    public void updateAuthor(UUID id, Author author) throws BaseErrorHandler {
        Author authorOrig = authorRepository.findById(id)
                .orElseThrow(() -> new BaseErrorHandler("Author with id " + id
                        + " not found."));
        if (!Objects.equals(author.getAuthorName(), authorOrig.getAuthorName()) &&
            authorRepository.getAuthorByAuthorName(author.getAuthorName()).isEmpty()){
            authorOrig.setAuthorName(author.getAuthorName());
        }
    }
}
