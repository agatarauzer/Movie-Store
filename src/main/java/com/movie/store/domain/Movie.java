package com.movie.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @NotEmpty(message = "Title is required")
    @Column(name = "TITLE", nullable = false)
    private String title;

    @NotEmpty(message = "Director is required")
    @Column(name = "DIRECTOR", nullable = false)
    private String director;

    @Column(name = "YEAR", length = 4)
    private int year;

    @Column(name = "GENRE")
    private String genre;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MOVIES_ACTORS", joinColumns = {@JoinColumn(name = "MOVIE_ID", referencedColumnName = "MOVIE_ID")},
    inverseJoinColumns = {@JoinColumn(name = "ACTOR_ID", referencedColumnName = "ACTOR_ID")})
    @JsonIgnoreProperties("movies")
    private List<Actor> actors = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "EVALUATION_ID")
    private UserEvaluation userEvaluation;

    public Movie(long id, String title, String director, int year, String genre, List<Actor> actors) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
        this.genre = genre;
        this.actors = actors;
    }
}