package com.agatarauzer.controllerRest;

import com.agatarauzer.model.Movie;
import com.agatarauzer.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class MovieControllerRest {

    private MoviesRepository moviesRepo;

    @Autowired
    public MovieControllerRest(MoviesRepository moviesRepo) {
        this.moviesRepo = moviesRepo;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Movie> getMovies(@RequestParam(defaultValue = "title") String orderBy) {
        List<Movie> movies = moviesRepo.findAll();
        if("title".equals(orderBy)) {
            movies.sort(Comparator.comparing(Movie::getTitle));
        }
        else if("director".equals(orderBy)) {
            movies.sort(Comparator.comparing(Movie::getDirector));
        }
        else if("year".equals(orderBy)) {
            movies.sort(Comparator.comparing(Movie::getYear));
        }
        return movies;
    }

    @GetMapping(path = "/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Movie> getMovie(@PathVariable Long id) {
        return moviesRepo.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveMovie(@RequestBody Movie movie) {
        moviesRepo.save(movie);
    }
}
