package com.movie.store.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.URL;

@AllArgsConstructor
@Getter
public class MovieFilmwebDto {
    private Long id;
    private String polishTitle;
    private String originalTitle;
    private Integer year;
    private URL posterURL;
    private URL websiteURL;
    private Float rate;
    private Integer votes;
    private String genre;
    private String productionCountry;
    private String plot;
}
