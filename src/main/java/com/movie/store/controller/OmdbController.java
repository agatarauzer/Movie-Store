package com.movie.store.controller;

import com.movie.store.domain.MovieOmdbDto;
import com.movie.store.service.OmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/movie-store")
public class OmdbController {

    @Autowired
    private OmdbService omdbService;

    @GetMapping(value = "/omdbInfo/{title}")
    public MovieOmdbDto getMovieInfoFromOmdb(@PathVariable String title) {
        return omdbService.getMovieOmdbInfo(title);
    }

}
