package com.movie.store.service;

import com.movie.store.domain.Actor;
import com.movie.store.domain.Movie;
import com.movie.store.repository.ActorsRepository;
import com.movie.store.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {
    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private ActorsRepository actorsRepository;

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

    public Actor saveActor(final Actor actor) {
        return actorsRepository.save(actor);
    }

    public List<Actor> getAllActors() {
        return actorsRepository.findAll();
    }

    public List<Actor> getActorsByFullName(String name) {
        return actorsRepository.findByName(name);
    }

    public List<Actor> getActorsByNameContaining(String word) {
        return actorsRepository.findByNameContaining(word);
    }

    public List<Actor> getActorsByMoviesWith(String title) {
        return actorsRepository.findActorsByMoviesContaining(title);
    }











}
