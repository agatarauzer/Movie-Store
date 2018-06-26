package com.movie.store.controllerRest;

import com.movie.store.mapper.MovieMapper;
import com.movie.store.domain.MovieDto;
import com.movie.store.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
@RequestMapping("/v2/movie-store")
public class MovieControllerRest {

    @Autowired
    private DbService service;

    @Autowired
    private MovieMapper movieMapper;

    @GetMapping(value = "getMovies")
    public List<MovieDto> getMovies() {
        return movieMapper.mapToMovieDtoList(service.getAllMovies());
    }

    @GetMapping(value = "getMovie")
    public MovieDto getMovieById(@RequestParam Long id) throws MovieNotFoundException {
        return movieMapper.mapToMovieDto(service.getMovie(id).orElseThrow(MovieNotFoundException::new));
    }

    @GetMapping(value = "getMoviesTitle")
    public List<MovieDto> getMoviesByTitle(@RequestParam String title) {
        return movieMapper.mapToMovieDtoList(service.getMovies(title));
    }

    @PostMapping(value = "addMovie", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addMovie(@RequestBody MovieDto movieDto) {
        service.saveMovie(movieMapper.mapToMovie(movieDto));
    }

    @DeleteMapping(value = "deleteMovie")
    public void deleteMovie(@RequestParam Long id) {
        service.deleteMovie(id);
    }
}