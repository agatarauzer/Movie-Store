package com.movie.store.controllerRest;

import com.movie.store.domain.Movie;
import com.movie.store.validators.MovieGenreValidator;
import com.movie.store.validators.MovieStatusValidator;
import com.movie.store.exceptions.GenreNotFoundException;
import com.movie.store.exceptions.MovieNotFoundException;
import com.movie.store.exceptions.StatusNotFoundException;
import com.movie.store.mapper.MovieMapper;
import com.movie.store.domain.MovieDto;
import com.movie.store.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
@RequestMapping("/v2/movie-store")
public class MovieController {

    @Autowired
    private MovieService service;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private MovieStatusValidator movieStatusValidator;

    @Autowired
    private MovieGenreValidator movieGenreValidator;

    @GetMapping(value = "movies")
    public List<MovieDto> getMovies() {
        List<Movie> movies = service.getAllMovies();
        return movieMapper.mapToMovieDtoList(movies);
    }

    @GetMapping(value = "movie-id")
    public MovieDto getMovieById(@RequestParam Long id) throws MovieNotFoundException {
        Movie movie = service.getMovie(id).orElseThrow(MovieNotFoundException::new);
        return movieMapper.mapToMovieDto(movie);
    }

    @GetMapping(value = "movie-title")
    public List<MovieDto> getMoviesByTitle(@RequestParam String title) {
        List<Movie> movies = service.getMovies(title);
        return movieMapper.mapToMovieDtoList(movies);
    }

    @PostMapping(value = "movie", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addMovie(@RequestBody MovieDto movieDto) {

        try {
            movieGenreValidator.validateGenre(movieDto.getGenre());
            movieStatusValidator.validateStatus(movieDto.getUserEvaluation().getStatus());
            Movie movie = movieMapper.mapToMovie(movieDto);
            service.saveMovie(movie);
        }
        catch (StatusNotFoundException | GenreNotFoundException e) {}
    }

    @DeleteMapping(value = "movie")
    public void deleteMovie(@RequestParam Long id) {
        service.deleteMovie(id);
    }

    @PutMapping(value = "movie")
    public MovieDto updateMovie(@RequestBody MovieDto movieDto) {

        try {
            movieGenreValidator.validateGenre(movieDto.getGenre());
            movieStatusValidator.validateStatus(movieDto.getUserEvaluation().getStatus());
            Movie movie = movieMapper.mapToMovie(movieDto);
            Movie savedMovie = service.saveMovie(movie);
            return movieMapper.mapToMovieDto(savedMovie);
        }
        catch (StatusNotFoundException |GenreNotFoundException e) {}

        return movieDto;
    }

    @GetMapping(value = "movies/watched")
    public List<MovieDto> getWatchedMovies() {
        List<Movie> watchedMovies = service.getAllWatchedMovies();
        return movieMapper.mapToMovieDtoList(watchedMovies);
    }

    @GetMapping(value = "movies/planned")
    public List<MovieDto> getMoviesPlannedToWatch() {
        List<Movie> watchList = service.getAllMoviesPlannedToWatch();
        return movieMapper.mapToMovieDtoList(watchList);
    }
}