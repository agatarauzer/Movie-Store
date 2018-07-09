package com.movie.store.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserEvaluationDto {
    private Long id;
    private int rating;
    private MovieStatus status;
    private LocalDate dateOfWatching;
    private Movie movie;
}
