package com.movie.store.service;

import com.movie.store.domain.Actor;
import com.movie.store.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public List<Actor> getActorsByFullName(String name) {
        return actorRepository.findByName(name);
    }

    public List<Actor> getActorsByNameContaining(String word) {
        return actorRepository.findByNameContaining(word);
    }

    public List<Actor> getActorsByMoviesWith(String title) {
        return actorRepository.findActorsPlayedInMovieWithTitle(title);
    }
}
