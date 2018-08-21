package com.movie.store.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MovieOmdbDto {

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Year")
    private String year;

    @JsonProperty("Released")
    private String date;

    @JsonProperty("Director")
    private String director;

    @JsonProperty("Actors")
    private String actors;

    @JsonProperty("Runtime")
    private String runtime;

    @JsonProperty("Plot")
    private String plot;

    @JsonProperty("Country")
    private String country;

    @JsonProperty("imdbRating")
    private String ratingIMD;

}
