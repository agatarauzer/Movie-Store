package com.movie.store.controllerRest;

import com.movie.store.domain.ActorDto;
import com.movie.store.mapper.ActorMapper;
import com.movie.store.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v2/movie-store")
public class ActorControllerRest {

    @Autowired
    private DbService service;

    @Autowired
    private ActorMapper actorMapper;

    @GetMapping(value = "getAllActors")
    public List<ActorDto> getAllActors() {
        return actorMapper.mapToListActorDto(service.getAllActors());
    }

    @GetMapping(value = "getActorsFullName")
    public List<ActorDto> getActorsByFullName(String name) {
        return actorMapper.mapToListActorDto(service.getActorsByFullName(name));
    }

    @GetMapping(value = "getActorsByName")
    public List<ActorDto> getActorsByNameContaining(String word) {
        return actorMapper.mapToListActorDto(service.getActorsByNameContaining(word));
    }

    @GetMapping(value = "getActorsInMovie")
    public List<ActorDto> getActorsByMovieTitle(String title) {
        return actorMapper.mapToListActorDto(service.getActorsByMoviesWith(title));
    }

}
