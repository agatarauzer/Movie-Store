package com.movie.store.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class ActorDto {
    private Long id;
    private String name;
    private List<Movie> movies;

    public ActorDto(long id, String name, List<Movie> movies) {
        this.id = id;
        this.name = name;
        this.movies = movies;
    }
}
