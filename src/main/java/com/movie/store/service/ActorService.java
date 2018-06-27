package com.movie.store.service;

import com.movie.store.domain.Actor;
import com.movie.store.repository.ActorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    @Autowired
    private ActorsRepository actorsRepository;

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
