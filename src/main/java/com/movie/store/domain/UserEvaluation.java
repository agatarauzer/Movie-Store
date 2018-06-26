package com.movie.store.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "USERS_EVALUATION")
public class UserEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "EVALUATION_ID")
    private Long id;

    @Column(name = "RATING")
    @Min(1)
    @Max(5)
    private int rating;

    @Column(name = "WATCHED")
    private boolean watched;

    @Column(name="DATE_OF_WATCHING")
    private LocalDate dateWatched;

    @Column(name = "PLANNED")
    private String planned;

    @OneToOne(mappedBy = "userEvaluation")
    private Movie movie;
}
