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
    private boolean watched;
    private LocalDate dateWatched;
    private String planned;
    private Movie movie;

}
