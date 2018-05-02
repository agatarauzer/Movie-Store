package com.agatarauzer.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
public class Movie implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "movie_id")
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "Title is required")
    private String title;

    @Column(nullable = false)
    @NotEmpty(message = "Director is required")
    private String director;

    @Column(length = 4)
    private Integer year;

    public Movie() {}

    public Movie(String title, String director, Integer year) {
        this.title = title;
        this.director = director;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movie number " + id
                + "\ntile: " + title
                + ", director: " + director
                + ", production year: " + year;
    }
}
