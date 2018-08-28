package com.movie.store.controller;

import com.movie.store.domain.MovieOmdbFullDto;
import com.movie.store.domain.OmdbSearchResults;
import com.movie.store.exception.MovieNotFoundInImdbException;
import com.movie.store.service.OmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/movie-store/omdb")
public class OmdbController {

    @Autowired
    private OmdbService omdbService;

    @GetMapping(value = "title/{title}")
    public MovieOmdbFullDto getMovieInfoByTitle(@PathVariable String title) throws MovieNotFoundInImdbException {
        return omdbService.getMovieOmdbInfoByTitle(title);
    }

    @GetMapping(value = "id/{id}")
    public MovieOmdbFullDto getMovieInfoById(@PathVariable String id) {
        return omdbService.getMovieOmdbInfoById(id);
    }

    @GetMapping(value = "word/{word}")
    public OmdbSearchResults getMoviesWithTitlesContaining(@PathVariable String word) {
        return omdbService.getMoviesFromOmdbByWord(word);
    }
}
