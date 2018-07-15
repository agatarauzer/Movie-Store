package com.movie.store.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "USERS_EVALUATIONS")
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

    @Column(name = "STATUS")
    private String status;

    @Column(name="DATE_OF_WATCHING")
    @JsonFormat(pattern = "dd MM yyyy")
    private LocalDate dateOfWatching;

    @Column(name = "COMMENT")
    private String comment;

    @OneToOne(mappedBy = "userEvaluation")
    private Movie movie;

}









