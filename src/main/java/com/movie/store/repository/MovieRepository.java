package com.movie.store.repository;

import com.movie.store.domain.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface MovieRepository extends CrudRepository<Movie, Long> {

    @Query(nativeQuery = true)
    List<Movie> getWatchedMovies();

    @Query(nativeQuery = true)
    List<Movie> getMoviesPlannedToWatch();

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
