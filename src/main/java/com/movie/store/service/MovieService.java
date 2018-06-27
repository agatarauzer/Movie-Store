package com.movie.store.service;

import com.movie.store.domain.Movie;
import com.movie.store.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MoviesRepository moviesRepository;

    public List<Movie> getAllMovies() {
        return moviesRepository.findAll();
    }

    public Optional<Movie> getMovie(final Long id) {
        return moviesRepository.findById(id);
    }

    public Movie saveMovie(final Movie movie) {
        return moviesRepository.save(movie);
    }

    public void deleteMovie(final Long id) {
        moviesRepository.deleteById(id);
    }

    public List<Movie> getMovies(String title) {
        return moviesRepository.findByTitle(title);
    }
}
