package com.nikron.springboot.librarybook.genre;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre getGenreId(Long genreId) {
        return genreRepository.getGenreById(genreId)
                .orElseThrow(() -> new IllegalStateException("Genre with id "
                        + genreId + " not found"));
    }

    public void addGenre(Genre genre) {
        if (genreRepository.getGenreByGenreName(genre.getGenreName())
                .isPresent()){
            throw new IllegalStateException("Genre with name " + genre.getGenreName()
                    + " already exists.");
        }
        genreRepository.save(genre);
    }

    public void deleteGenre(Long genreId) {
        if (genreRepository.getGenreById(genreId).isEmpty()){
            throw new IllegalStateException("Genre with id " + genreId
                    + " not found.");
        }
        genreRepository.deleteById(genreId);
    }

    @Transactional
    public void updateGenre(Long genreId, String genreName) {
        Genre genre = genreRepository.getGenreById(genreId)
                .orElseThrow(() -> new IllegalStateException("Genre with id " + genreId
                        + " not found."));
        if (genreName != null && genreName.length() > 3
                && !Objects.equals(genre.getGenreName(), genreName)){
            genre.setGenreName(genreName);
        }
    }
}
