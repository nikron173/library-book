package com.nikron.springboot.librarybook.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    @Query("SELECT a FROM Genre a WHERE a.id = ?1")
    Optional<Genre> getGenreById(Long id);

    @Query("SELECT a FROM Genre a WHERE a.genreName = ?1")
    Optional<Genre> getGenreByGenreName(String name);
}
