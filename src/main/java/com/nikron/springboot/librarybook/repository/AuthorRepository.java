package com.nikron.springboot.librarybook.repository;

import com.nikron.springboot.librarybook.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {
    @Query("SELECT a FROM Author a WHERE a.authorName = ?1")
    Optional<Author> getAuthorByAuthorName(String name);
}
