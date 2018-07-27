package com.movie.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NamedNativeQuery(name = "Actor.findActorsPlayedInMovieWithTitle",
        query = "SELECT * FROM Actors " +
                "   JOIN Movies_Actors ON Actors.actor_id = Movies_Actors.actor_id " +
                "   JOIN Movies ON Movies.movie_id = Movies_Actors.movie_id " +
                "WHERE Movies.title LIKE CONCAT('%', :title, '%') " +
                "ORDER BY Actors.name",
        resultClass = Actor.class)

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACTORS")

public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACTOR_ID", unique = true)
    private long id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "actors")
    @JsonIgnoreProperties("actors")
    private List<Movie> movies = new ArrayList<>();
}
