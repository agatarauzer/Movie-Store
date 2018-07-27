package com.movie.store.controller;

import com.movie.store.domain.Actor;
import com.movie.store.domain.ActorDto;
import com.movie.store.mapper.ActorMapper;
import com.movie.store.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/movie-store")
public class ActorController {

    @Autowired
    private ActorService service;

    @Autowired
    private ActorMapper actorMapper;

    @GetMapping(value = "actors")
    public List<ActorDto> getActors() {
        List<Actor> actors = service.getAllActors();
        return actorMapper.mapToListActorDto(actors);
    }

    @GetMapping(value = "actors/name")
    public List<ActorDto> getActorsByNameContaining(@RequestParam String word) {
        List<Actor> actors = service.getActorsByNameContaining(word);
        return actorMapper.mapToListActorDto(actors);
    }

    @GetMapping(value = "actors/movie")
    public List<ActorDto> getActorsByMovieTitle(@RequestParam String title) {
        List<Actor> actors = service.getActorsByMoviesWith(title);
        return actorMapper.mapToListActorDto(actors);
    }
}
