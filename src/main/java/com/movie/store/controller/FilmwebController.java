package com.movie.store.controller;


import com.movie.store.domain.MovieFilmwebDto;
import com.movie.store.service.FilmwebService;
import info.talacha.filmweb.connection.FilmwebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v2/movie-store")
public class FilmwebController {

    @Autowired
    private FilmwebService service;

    @GetMapping(value = "filmweb")
    public List<MovieFilmwebDto> getInfoFromFilmwebAboutMovie(String title) throws FilmwebException {
        return service.getFullMovieInfo(title);
    }
}
