package com.nikron.springboot.librarybook.service;

import com.nikron.springboot.librarybook.entity.Genre;
import com.nikron.springboot.librarybook.error.BaseErrorHandler;
import com.nikron.springboot.librarybook.repository.GenreRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GenreService {
    @NonNull private final GenreRepository genreRepository;

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre getGenreId(UUID id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Genre with id "
                        + id + " not found"));
    }

    public void addGenre(Genre genre) throws BaseErrorHandler {
        if (genreRepository.getGenreByGenreName(genre.getGenreName())
                .isPresent()){
            throw new BaseErrorHandler("Genre with name " + genre.getGenreName()
                    + " already exists.");
        }
        genreRepository.save(genre);
    }

    public void deleteGenre(UUID id) throws BaseErrorHandler {
        if (genreRepository.findById(id).isEmpty()){
            throw new BaseErrorHandler("Genre with id " + id
                    + " not found.");
        }
        genreRepository.deleteById(id);
    }

    @Transactional
    public void updateGenre(UUID id, Genre genre) throws BaseErrorHandler {
        Genre genreOrig = genreRepository.findById(id)
                .orElseThrow(() -> new BaseErrorHandler("Genre with id " + id
                        + " not found."));
        if (!Objects.equals(genre.getGenreName(), genreOrig.getGenreName()) &&
            genreRepository.getGenreByGenreName(genre.getGenreName()).isEmpty()){
            genreOrig.setGenreName(genre.getGenreName());
        }
    }
}
