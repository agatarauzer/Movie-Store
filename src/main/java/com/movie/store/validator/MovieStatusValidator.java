package com.movie.store.validator;

import com.movie.store.exception.StatusNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class MovieStatusValidator {

    private Set<String> statuses = new HashSet<>(Arrays.asList("watched", "planned"));

    public void validateStatus(String status) throws StatusNotFoundException{
        if (!statuses.contains(status)) {
            throw new StatusNotFoundException();
        }
    }
}
