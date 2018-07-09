package com.movie.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACTORS")
@NamedQuery(name = "Actor.findActorsPlayedInMovieWithTitle",
        query = "FROM Actor a JOIN a.movies m WHERE m.title = :TITLE"
)

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
