package com.movie.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NamedNativeQueries({
        @NamedNativeQuery(name = "Movie.getWatchedMovies",
                query = "SELECT * " +
                        "FROM Movies " +
                        "JOIN Users_Evaluations ON Movies.evaluation_id = Users_Evaluations.evaluation_id " +
                        "WHERE Users_Evaluations.status = 'watched' " +
                        "ORDER BY Movies.title",
                resultClass = Movie.class
        ),
        @NamedNativeQuery(name = "Movie.getMoviesPlannedToWatch",
                query = "SELECT * " +
                        "FROM Movies " +
                        "JOIN Users_Evaluations ON Movies.evaluation_id = Users_Evaluations.evaluation_id " +
                        "WHERE Users_Evaluations.status = 'planned' " +
                        "ORDER BY Movies.title",
                resultClass = Movie.class
        ),
        @NamedNativeQuery(name = "Movie.getMoviesWithRating",
                query = "SELECT * " +
                        "FROM Movies " +
                        "JOIN Users_Evaluations ON Movies.evaluation_id = Users_Evaluations.evaluation_id " +
                        "WHERE Users_Evaluations.rating = :rating " +
                        "ORDER BY Movies.title",
                resultClass = Movie.class)
})

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MOVIES")
public class Movie implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MOVIE_ID", unique = true)
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DIRECTOR", nullable = false)
    private String director;

    @Column(name = "YEAR", length = 4)
    private int year;

    @Column(name = "GENRE")
    private String genre;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "MOVIES_ACTORS", joinColumns = {@JoinColumn(name = "MOVIE_ID", referencedColumnName = "MOVIE_ID")},
    inverseJoinColumns = {@JoinColumn(name = "ACTOR_ID", referencedColumnName = "ACTOR_ID")})
    @JsonIgnoreProperties("movies")
    private List<Actor> actors = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "EVALUATION_ID")
    @JsonIgnoreProperties("movie")
    private UserEvaluation userEvaluation;

}
