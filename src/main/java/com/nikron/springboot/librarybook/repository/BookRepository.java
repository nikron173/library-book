package com.nikron.springboot.librarybook.repository;

import com.nikron.springboot.librarybook.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    @Query("SELECT b FROM Book b WHERE b.bookName = ?1")
    Optional<Book> getBookByBookName(String name);

    @Query("SELECT b FROM Book b WHERE (b.bookName = :bookName or :bookName IS NULL)" +
            " and (b.author.authorName = :authorName or :authorName IS NULL)" +
            " and (b.genre.genreName = :genreName or :genreName IS NULL)")
    List<Book> getFiltersBooks(@Param("bookName") String bookName, @Param("authorName") String authorName,
                               @Param("genreName") String genreName);
}
