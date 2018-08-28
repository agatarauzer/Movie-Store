package com.movie.store.Omdb;

import com.movie.store.domain.MovieOmdbFullDto;
import com.movie.store.domain.OmdbSearchResults;
import com.movie.store.exception.MovieNotFoundInImdbException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class OmdbClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OmdbConfig omdbConfig;

    public MovieOmdbFullDto getMovieInfoByTitle(String title) throws MovieNotFoundInImdbException {
        MovieOmdbFullDto movie = restTemplate.getForObject(buildURLToGetMovieInfoByTitle(title), MovieOmdbFullDto.class);
        if (movie.getOmdbId().equals(null)) {
            throw new MovieNotFoundInImdbException();
        }
        return movie;
    }

    public MovieOmdbFullDto getMovieInfoById(String id) throws MovieNotFoundInImdbException {
        MovieOmdbFullDto movie = restTemplate.getForObject(buildURLToGetMovieInfoById(id), MovieOmdbFullDto.class);
        if (movie.getOmdbId().equals(null)) {
            throw new MovieNotFoundInImdbException();
        }
        return movie;
    }

    public OmdbSearchResults getMovies(String word) throws MovieNotFoundInImdbException {
        OmdbSearchResults result = restTemplate.getForObject(buildURLToSearchMoviesByWord(word), OmdbSearchResults.class);
        if (result.getMovies().equals(null)) {
            throw new MovieNotFoundInImdbException();
        }
        return result;
    }

    private URI buildURLToGetMovieInfoByTitle(String title) {
        return UriComponentsBuilder.fromHttpUrl(omdbConfig.getOmdbApiEndpoint()
        + "?apikey=" + omdbConfig.getOmdbApiKey() + "&type=movie" + "&t=" + title)
                .build()
                .encode()
                .toUri();
    }

    private URI buildURLToGetMovieInfoById(String id) {
        return UriComponentsBuilder.fromHttpUrl(omdbConfig.getOmdbApiEndpoint()
        + "?apikey=" + omdbConfig.getOmdbApiKey() + "&type=movie" + "&i=" + id)
                .build()
                .encode()
                .toUri();
    }

    private URI buildURLToSearchMoviesByWord(String word) {
        return UriComponentsBuilder.fromHttpUrl(omdbConfig.getOmdbApiEndpoint()
                + "?apikey=" + omdbConfig.getOmdbApiKey() + "&type=movie" + "&s=" + word)
                .build()
                .encode()
                .toUri();
    }
}
