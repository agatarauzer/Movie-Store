package com.movie.store.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "MOVIES")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "MOVIE_ID", unique = true)
    private Long id;

    @NotEmpty(message = "Title is required")   //czy ta walidacja powinna być w entity czy w DTO, czy w obu miejscach?
    @Column(name = "TITLE", nullable = false)
    private String title;

    @NotEmpty(message = "Director is required")
    @Column(name = "DIRECTOR", nullable = false)
    private String director;

    @Column(name = "YEAR", length = 4)
    private int year;

    @Column(name = "GENRE")
    private String genre;

    @Column(name = "RATING")
    @Min(1)
    @Max(5)
    private int rating;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MOVIES_ACTORS", joinColumns = {@JoinColumn(name = "MOVIE_ID", referencedColumnName = "MOVIE_ID")},
    inverseJoinColumns = {@JoinColumn(name = "ACTOR_ID", referencedColumnName = "ACTOR_ID")})
    private List<Actor> actors;

    public Movie(long id, String title, String director, int year, String genre, int rating) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
        actors = new ArrayList<>();
    }
}
