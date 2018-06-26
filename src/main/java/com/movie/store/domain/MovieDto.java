package com.movie.store.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class MovieDto {

    private long id;
    private String title;
    private String director;
    private int year;
    private String genre;
    private List<Actor> actors = new ArrayList<>();

    public MovieDto(Long id, String title, String director, int year, String genre, List<Actor> actors) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
        this.genre = genre;
        this.actors = actors;
    }
}
