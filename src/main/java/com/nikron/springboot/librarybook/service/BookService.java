package com.nikron.springboot.librarybook.service;

import com.nikron.springboot.librarybook.entity.Author;
import com.nikron.springboot.librarybook.repository.AuthorRepository;
import com.nikron.springboot.librarybook.entity.Book;
import com.nikron.springboot.librarybook.entity.Genre;
import com.nikron.springboot.librarybook.repository.BookRepository;
import com.nikron.springboot.librarybook.repository.GenreRepository;
import com.nikron.springboot.librarybook.entity.Student;
import com.nikron.springboot.librarybook.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository,
                       GenreRepository genreRepository,
                       StudentRepository studentRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.studentRepository = studentRepository;
    }

    public List<Book> getBooks(String authorName, String genreName,
                               String bookName, String studentName) {
//        if (authorName != null && genreName != null && bookName != null) {
//            return bookRepository
//                    .getBookByGenreNameAndBookNameAndBookName(authorName, genreName, bookName)
//                    .stream().toList();
//        } else if (authorName != null && genreName != null) {
//            return bookRepository
//                    .getBookByAuthorNameAndGenreName(authorName, genreName)
//                    .stream().toList();
//        } else if (authorName != null && bookName != null) {
//            return bookRepository
//                    .getBookByAuthorNameAndBookName(authorName, bookName)
//                    .stream().toList();
//        } else if (genreName != null && bookName != null) {
//            return bookRepository
//                    .getBookByGenreNameAndBookName(genreName, bookName)
//                    .stream().toList();
//        } else if (authorName != null) {
//            return bookRepository.getBookByAuthorName(authorName)
//                    .stream().toList();
//        } else if (genreName != null) {
//            return bookRepository.getBookByGenreName(genreName)
//                    .stream().toList();
//        } else if (bookName != null) {
//            return bookRepository.getBookByBookName(bookName).stream().toList();
//        } else {
//            return bookRepository.findAll();
//        }
        return bookRepository
                .getBookFilters(authorName, genreName, bookName, studentName)
                .stream().toList();
    }

    public Book getBookId(Long bookId) {
        return bookRepository.getBookById(bookId)
                .orElseThrow(() -> new IllegalStateException("Book with id "
                        + bookId + " not found"));
    }

    public void addBook(Book book) {
        if (bookRepository.getBookByBookName(book.getBookName())
                .isPresent()){
            throw new IllegalStateException("Book with name " + book.getBookName()
                    + " already exists.");
        }
        if (authorRepository.getAuthorByAuthorName(book.getAuthor().getAuthorName()).isEmpty()){
            authorRepository.save(book.getAuthor());
        }
        if (genreRepository.getGenreByGenreName(book.getGenre().getGenreName()).isEmpty()){
            genreRepository.save(book.getGenre());
        }
        bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        if (bookRepository.getBookById(bookId).isEmpty()){
            throw new IllegalStateException("Book with id " + bookId
                    + " not found.");
        }
        bookRepository.deleteById(bookId);
    }

    @Transactional
    public void updateBook(Long bookId, String authorName,
                           String genreName, String bookName, Integer createDate,
                           String studentName) {
        Book book = bookRepository.getBookById(bookId)
                .orElseThrow(() -> new IllegalStateException("Book with id " + bookId
                        + " not found."));
        Author author = authorName != null ? authorRepository.getAuthorByAuthorName(authorName)
                .orElseThrow(() -> new IllegalStateException("Author with name " + authorName
                        + " not found.")) : null;
        Genre genre = genreName != null ? genreRepository.getGenreByGenreName(genreName)
                .orElseThrow(() -> new IllegalStateException("Genre with name " + genreName
                        + " not found.")) : null;
        Student student = studentName != null ? studentRepository.getStudentByName(studentName)
                .orElseThrow(() -> new IllegalStateException("Student with name " + studentName
                        + " not found.")) : null;

        if (bookName != null && bookName.length() > 3
                && !Objects.equals(book.getBookName(), bookName)){
            book.setBookName(bookName);
        }
        if (createDate != null && createDate > 0
                && !Objects.equals(book.getCreateDate(), createDate)){
            book.setCreateDate(createDate);
        }

        if (author != null && !Objects.equals(book.getAuthor(), author)){
            book.setAuthor(author);
        }

        if (genre != null && !Objects.equals(book.getGenre(), genre)){
            book.setGenre(genre);
        }
        if (student != null && !Objects.equals(book.getStudent(), student)){
            book.setStudent(student);
        }
    }
//    public List<Book> getBookByAuthorOrByGenre(String genreName, String authorName) {
//        if (authorName != null && genreName != null){
//            return bookRepository
//                    .getBookByAuthorNameAndGenreName(genreName, authorName)
//                    .stream().toList();
//        } else if (authorName != null) {
//            return bookRepository
//                    .getBookByAuthorName(authorName).stream().toList();
//        } else if (genreName != null){
//            return bookRepository
//                    .getBookByGenreName(genreName).stream().toList();
//        }
//        return null;
//    }
}
