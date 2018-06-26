package com.movie.store.repository;

import com.movie.store.domain.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MoviesRepository extends CrudRepository<Movie, Long> {


    @Override
    List<Movie> findAll();

    @Override
    Movie save(Movie movie);

    @Override
    Optional<Movie> findById(Long id);

    @Override
    void deleteById(Long id);

    List<Movie> findByTitle(String title);

}
