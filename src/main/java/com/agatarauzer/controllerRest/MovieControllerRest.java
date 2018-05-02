package com.agatarauzer.controllerRest;

import com.agatarauzer.model.Movie;
import com.agatarauzer.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
public class MovieControllerRest {

    private MoviesRepository moviesRepository;

    @Autowired
    public MovieControllerRest(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @GetMapping(path = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movie>> getAllMovies(@RequestParam(defaultValue = "title") String orderBy) {
        List<Movie> movies = moviesRepository.findAll();
        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        if ("title".equals(orderBy)) {
            movies.sort(Comparator.comparing(Movie::getTitle));
        }
        else if ("director".equals(orderBy)) {
            movies.sort(Comparator.comparing(Movie::getDirector));
        }
        else if ("year".equals(orderBy)) {
            movies.sort(Comparator.comparing(Movie::getYear));
        }
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Movie> getMovieById(@PathVariable("id") Long id) {
        return moviesRepository.findById(id);
    }

    @GetMapping(path = "/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movie>> getMovieByTitle(@PathVariable("title") String title) {
        List<Movie> moviesByTitle = moviesRepository.findByTitle(title);
        if (moviesByTitle.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(moviesByTitle, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> saveMovie(@Valid @RequestBody Movie movie) {
        moviesRepository.save(movie);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }
}
