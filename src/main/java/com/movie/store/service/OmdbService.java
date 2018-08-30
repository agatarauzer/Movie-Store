package com.movie.store.service;

import com.movie.store.Omdb.OmdbClient;
import com.movie.store.domain.MovieOmdbFullDto;
import com.movie.store.domain.OmdbSearchResults;
import com.movie.store.exception.MovieNotFoundInImdbException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OmdbService {

    @Autowired
    private OmdbClient omdbClient;

    public MovieOmdbFullDto getMovieOmdbInfoByTitle(String title) throws MovieNotFoundInImdbException {
        return omdbClient.getMovieInfoByTitle(title);
    }

    public MovieOmdbFullDto getMovieOmdbInfoById(String id) throws MovieNotFoundInImdbException {
        return omdbClient.getMovieInfoById(id);
    }

    public OmdbSearchResults getMoviesFromOmdbByWord(String word) throws MovieNotFoundInImdbException {
        return omdbClient.getMovies(word);
    }
}
