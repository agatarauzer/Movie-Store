package com.movie.store.Omdb;

import com.movie.store.domain.MovieOmdbDto;
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

    public MovieOmdbDto getMovieInfo(String title) {
        return restTemplate.getForObject(buildURL(title), MovieOmdbDto.class);
    }

    private URI buildURL(String title) {
        return UriComponentsBuilder.fromHttpUrl(omdbConfig.getOmdbApiEndpoint()
        + "?apikey=" + omdbConfig.getOmdbApiKey() + "&t=" + title)
                .build()
                .encode()
                .toUri();
    }
}
