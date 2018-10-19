package com.movie.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieOmdbBasicDto {

    @JsonProperty("imdbID")
    private String omdbId;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Year")
    private String year;
}
