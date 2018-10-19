package com.movie.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieOmdbFullDto {

    @JsonProperty("imdbID")
    private String omdbId;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Year")
    private String year;

    @JsonProperty("Released")
    private String date;

    @JsonProperty("Director")
    private String director;

    @JsonProperty("Actors")
    private List<String> actors;

    @JsonProperty("Runtime")
    private String runtime;

    @JsonProperty("Plot")
    private String plot;

    @JsonProperty("Country")
    private List<String> countries;

    @JsonProperty("imdbRating")
    private String ratingOmbd;

    @JsonSetter("Actors")
    public void setActors(String actorsList) {
        this.actors = Arrays.asList(actorsList.split(", "));
    }

    @JsonSetter("Country")
    public void setCountries(String countriesList) {
        this.countries = Arrays.asList(countriesList.split(", "));
    }
}
