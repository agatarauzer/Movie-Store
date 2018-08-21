package com.movie.store.service;

import com.movie.store.Omdb.OmdbClient;
import com.movie.store.domain.MovieOmdbDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OmdbService {

    @Autowired
    private OmdbClient omdbClient;

    public MovieOmdbDto getMovieOmdbInfo(String title) {
        return omdbClient.getMovieInfo(title);
    }

}
