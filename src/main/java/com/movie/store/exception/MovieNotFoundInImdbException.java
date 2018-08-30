package com.movie.store.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "no such movie in IMDB database")
public class MovieNotFoundInImdbException extends RuntimeException {

    public MovieNotFoundInImdbException(String message) {
        super(message);
    }
}
