package com.movie.store.repository;

import com.movie.store.domain.Actor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActorRepository extends CrudRepository<Actor, Long> {

    @Override
    List<Actor> findAll();

    List<Actor> findByName(String name);

    List<Actor> findByNameContaining(String name);

    @Query
    List<Actor> findActorsPlayedInMovieWithTitle(@Param("TITLE") String title);
}
