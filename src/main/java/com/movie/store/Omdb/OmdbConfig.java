package com.movie.store.Omdb;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class OmdbConfig {

    @Value("${omdb.api.endpoint.prod}")
    private String omdbApiEndpoint;

    @Value("${omdb.api.key}")
    private String omdbApiKey;
}
