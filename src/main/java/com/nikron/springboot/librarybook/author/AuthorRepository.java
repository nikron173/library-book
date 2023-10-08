package com.nikron.springboot.librarybook.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a WHERE a.id = ?1")
    Optional<Author> getAuthorById(Long id);

    @Query("SELECT a FROM Author a WHERE a.authorName = ?1")
    Optional<Author> getAuthorByAuthorName(String name);
}
