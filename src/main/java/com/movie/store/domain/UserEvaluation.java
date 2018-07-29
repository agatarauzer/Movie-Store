package com.movie.store.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    @Max(10)
    private int rating;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "DATE_OF_WATCHING")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateOfWatching;

    @Column(name = "COMMENT")
    private String comment;

    @OneToOne(mappedBy = "userEvaluation")
    private Movie movie;

    public UserEvaluation(long id, int rating, String status, LocalDate dateOfWatching, String comment) {
        this.id = id;
        this.rating = rating;
        this.status = status;
        this.dateOfWatching = dateOfWatching;
        this.comment = comment;
    }
}









