package com.movie.store.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OmdbSearchResults {

    @JsonProperty("Search")
    private List<MovieOmdbBasicDto> movies;

    @JsonProperty("totalResults")
    private int totalResults;
}
