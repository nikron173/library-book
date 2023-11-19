package com.nikron.springboot.librarybook.service;

import com.nikron.springboot.librarybook.dto.BookDTO;
import com.nikron.springboot.librarybook.dto.FilterBookDTO;
import com.nikron.springboot.librarybook.entity.Author;
import com.nikron.springboot.librarybook.entity.Genre;
import com.nikron.springboot.librarybook.error.BaseErrorHandler;
import com.nikron.springboot.librarybook.repository.AuthorRepository;
import com.nikron.springboot.librarybook.entity.Book;
import com.nikron.springboot.librarybook.repository.BookRepository;
import com.nikron.springboot.librarybook.repository.GenreRepository;
import com.nikron.springboot.librarybook.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookService {
    @NonNull private final BookRepository bookRepository;
    @NonNull private final AuthorRepository authorRepository;
    @NonNull private final GenreRepository genreRepository;
    @NonNull private final StudentRepository studentRepository;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBookId(UUID id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Book with id "
                        + id + " not found"));
    }


    //Здесь нужно подумать про сохранение объектов жанра и автора (uuid нет)
    public UUID addBook(Book book) throws BaseErrorHandler {
        if (bookRepository.getBookByBookName(book.getBookName())
                .isPresent()){
            throw new BaseErrorHandler("Book with name " + book.getBookName()
                    + " already exists.");
        }
        Optional<Author> author = authorRepository.getAuthorByAuthorName(book.getAuthor().getAuthorName());
        Optional<Genre> genre = genreRepository.getGenreByGenreName(book.getGenre().getGenreName());
        if (author.isEmpty()){
            book.setAuthor(authorRepository.save(book.getAuthor()));
        }
        if (genre.isEmpty()){
            book.setGenre(genreRepository.save(book.getGenre()));
        }
        book.setId(bookRepository.save(book).getId());
        return book.getId();
    }

    public void deleteBook(UUID id) throws BaseErrorHandler {
        if (bookRepository.findById(id).isEmpty()){
            throw new BaseErrorHandler("Book with id " + id
                    + " not found.");
        }
        bookRepository.deleteById(id);
    }

    @Transactional
    public void updateBook(UUID id, Book book) throws BaseErrorHandler {
        Book bookOrig = bookRepository.findById(id)
                .orElseThrow(() -> new BaseErrorHandler("Book with id " + id
                        + " not found."));
        if (!Objects.equals(book.getBookName(), bookOrig.getBookName()) &&
            bookRepository.getBookByBookName(book.getBookName()).isEmpty()){
            bookOrig.setBookName(book.getBookName());
        }
        if (!Objects.equals(book.getCreateDate(), bookOrig.getCreateDate())){
            bookOrig.setCreateDate(book.getCreateDate());
        }
        if (!Objects.equals(book.getAuthor().getAuthorName(),
                bookOrig.getAuthor().getAuthorName()) && authorRepository
                .getAuthorByAuthorName(book.getAuthor().getAuthorName()).isPresent()){
            bookOrig.getAuthor().setAuthorName(book.getAuthor().getAuthorName());
        }
        if (!Objects.equals(book.getGenre().getGenreName(), bookOrig.getGenre().getGenreName()) &&
        genreRepository.getGenreByGenreName(book.getGenre().getGenreName()).isPresent()){
            bookOrig.getGenre().setGenreName(book.getGenre().getGenreName());
        }
    }

    public List<Book> getFilterBooks(FilterBookDTO filterBookDTO){
        return bookRepository.getFiltersBooks(filterBookDTO.getBookName(), filterBookDTO.getAuthorName(),
                filterBookDTO.getGenreName());
    }
}
