package com.movie.store.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private long id;
    private String title;
    private String director;
    private int year;
    private GenresOfMovies genre;
    private List<Actor> actors = new ArrayList<>();
    private UserEvaluation userEvaluation;
}
