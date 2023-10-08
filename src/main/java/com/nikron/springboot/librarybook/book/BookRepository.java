package com.nikron.springboot.librarybook.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.id = ?1")
    Optional<Book> getBookById(Long id);

    @Query("SELECT b FROM Book b WHERE b.bookName = ?1")
    Optional<Book> getBookByBookName(String name);

    @Query("SELECT b FROM Book b WHERE b.author.authorName = ?1")
    Optional<Book> getBookByAuthorName(String authorName);

    @Query("SELECT b FROM Book b WHERE b.genre.genreName = ?1")
    Optional<Book> getBookByGenreName(String genreName);

    @Query("SELECT b FROM Book b WHERE (:bookName is null or b.bookName = :bookName) and " +
            "(:authorName is null or b.author.authorName = :authorName) and " +
            "(:genreName is null or b.genre.genreName = :genreName) and " +
            "(:studenName is null or b.student.name = :studentName)")
    List<Book> getBookFilters(@Param("authorName") String authorName,
                              @Param("genreName") String genreName,
                              @Param("bookName") String bookName,
                              @Param("studentName") String studentName);


//    @Query("SELECT b FROM Book b WHERE b.author.authorName = ?1 and b.genre.genreName = ?2")
//    Optional<Book> getBookByAuthorNameAndGenreName(String authorName, String genreName);
//
//    @Query("SELECT b FROM Book b WHERE b.author.authorName = ?1 and b.bookName = ?2")
//    Optional<Book> getBookByAuthorNameAndBookName(String authorName, String bookName);
//
//    @Query("SELECT b FROM Book b WHERE b.genre.genreName = ?1 and b.bookName = ?2")
//    Optional<Book> getBookByGenreNameAndBookName(String genreName, String bookName);
//
//    @Query("SELECT b FROM Book b WHERE b.genre.genreName = ?1 and b.genre.genreName = ?2 and b.bookName = ?3")
//    Optional<Book> getBookByGenreNameAndBookNameAndBookName(String authorName, String genreName, String bookName);
//
}
