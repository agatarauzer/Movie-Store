package com.movie.store.mapper;

import com.movie.store.domain.Actor;
import com.movie.store.domain.ActorDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ActorMapper {

    public ActorDto mapToActorDto(Actor actor) {
        return new ActorDto (actor.getId(),
                actor.getName(),
                actor.getMovies());
    }

    public Actor mapToActor(ActorDto actorDto) {
        return new Actor(actorDto.getId(),
                        actorDto.getName(),
                        actorDto.getMovies());
    }

    public List<ActorDto> mapToListActorDto(List<Actor> actors) {
        return actors.stream()
                .map(a -> new ActorDto(a.getId(), a.getName(), a.getMovies()))
                .collect(Collectors.toList());
    }
}
