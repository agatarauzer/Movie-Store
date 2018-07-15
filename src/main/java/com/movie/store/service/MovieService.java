package com.movie.store.service;

import com.movie.store.domain.Movie;
import com.movie.store.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovie(final Long id) {
        return movieRepository.findById(id);
    }

    public Movie saveMovie(final Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteMovie(final Long id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> getMovies(String title) {
        return movieRepository.findByTitle(title);
    }

    public List<Movie> getAllWatchedMovies() {
        return movieRepository.getWatchedMovies();
    }

    public List<Movie> getAllMoviesPlannedToWatch() {
        return movieRepository.getMoviesPlannedToWatch();
    }

    public List<Movie> getAllMoviesWithRating(int rating) {
        return movieRepository.getMoviesWithRating(rating);
    }
}
