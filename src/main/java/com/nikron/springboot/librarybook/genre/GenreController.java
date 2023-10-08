package com.nikron.springboot.librarybook.genre;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/genre")
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<Genre> getAllGenres(){
        return genreService.getAllGenres();
    }

    @GetMapping(path = "{genreId}")
    public Genre getGenreId(@PathVariable(name = "genreId") Long genreId){
        return genreService.getGenreId(genreId);
    }

    @PostMapping
    public void addGenre(@RequestBody Genre genre){
        genreService.addGenre(genre);
    }

    @DeleteMapping(path = "{genreId}")
    public void deleteGenre(@PathVariable(name = "genreId") Long genreId){
        genreService.deleteGenre(genreId);
    }


    @PutMapping(path = "{genreId}")
    public void updateGenre(@PathVariable(name = "genreId") Long genreId,
                             @RequestParam(name = "genreName") String genreName){
        genreService.updateGenre(genreId, genreName);
    }
}
