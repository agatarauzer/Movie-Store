package com.agatarauzer.controllerRest;

import com.agatarauzer.model.Movie;
import com.agatarauzer.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import javax.validation.Valid;
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
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = moviesRepository.findAll();
        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping(path = "/search/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Movie> getMovieById(@PathVariable Long id) {
        return moviesRepository.findById(id);
    }

    @GetMapping(path = "/search/{title:[a-zA-Z]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movie>> getMoviesByTitle(@PathVariable String title) {
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
