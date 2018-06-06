package com.movie.store.model;

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
    private int rating;
    private List<Actor> actors;

    public MovieDto(Long id, String title, String director, int year, String genre, int rating) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
        actors = new ArrayList<>();
    }
}
