package com.movie.store.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private long id;

    @NotEmpty(message = "Title is required")
    private String title;

    @NotEmpty(message = "Director is required")
    private String director;

    private int year;
    private String genre;
    private List<Actor> actors = new ArrayList<>();
    private UserEvaluation userEvaluation;
}
