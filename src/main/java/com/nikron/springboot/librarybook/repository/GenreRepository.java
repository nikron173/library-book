package com.nikron.springboot.librarybook.repository;

import com.nikron.springboot.librarybook.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GenreRepository extends JpaRepository<Genre, UUID> {
    @Query("SELECT a FROM Genre a WHERE a.genreName = ?1")
    Optional<Genre> getGenreByGenreName(String name);
}
