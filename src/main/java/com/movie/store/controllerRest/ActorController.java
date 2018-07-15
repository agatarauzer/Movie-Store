package com.movie.store.controllerRest;

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

    @GetMapping(value = "getActors")
    public List<ActorDto> getActors() {
        return actorMapper.mapToListActorDto(service.getAllActors());
    }

    @GetMapping(value = "getActorsFullName")
    public List<ActorDto> getActorsByFullName(@RequestParam String name) {
        return actorMapper.mapToListActorDto(service.getActorsByFullName(name));
    }

    @GetMapping(value = "getActorsByName")
    public List<ActorDto> getActorsByNameContaining(@RequestParam String word) {
        return actorMapper.mapToListActorDto(service.getActorsByNameContaining(word));
    }

    @GetMapping(value = "getActorsInMovie")
    public List<ActorDto> getActorsByMovieTitle(@RequestParam String title) {
        return actorMapper.mapToListActorDto(service.getActorsByMoviesWith(title));
    }
}
