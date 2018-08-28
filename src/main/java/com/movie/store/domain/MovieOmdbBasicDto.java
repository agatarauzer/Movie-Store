package com.movie.store.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieOmdbBasicDto {

    @JsonProperty("imdbID")
    private String omdbId;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Year")
    private String year;
}
