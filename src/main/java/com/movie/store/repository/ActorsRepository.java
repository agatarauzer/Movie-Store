package com.movie.store.repository;

import com.movie.store.domain.Actor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActorsRepository extends CrudRepository<Actor, Long> {

    @Override
    Actor save(Actor actor);

    @Override
    List<Actor> findAll();

    List<Actor> findByName(String name);

    List<Actor> findByNameContaining(String name);

    List<Actor> findActorsByMoviesContaining(String title);


}
