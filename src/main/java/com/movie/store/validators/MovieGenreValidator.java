package com.movie.store.validators;

import com.movie.store.exceptions.GenreNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class MovieGenreValidator {

    private Set<String> genres = new HashSet<>(Arrays.asList("action", "adventure", "comedy",
            "crime", "drama", "historical", "horror", "musical", "science fiction", "war", "western"
            ));

    public void validateGenre(String genre) throws GenreNotFoundException {
        if (!genres.contains(genre)) {
            throw new GenreNotFoundException();
        }
    }
}


