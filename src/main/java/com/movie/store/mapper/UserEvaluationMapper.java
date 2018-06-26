package com.movie.store.mapper;

import com.movie.store.domain.UserEvaluation;
import com.movie.store.domain.UserEvaluationDto;

public class UserEvaluationMapper {

    public UserEvaluation mapToUserEvaluation(final UserEvaluationDto userEvaluationDto) {
        return new UserEvaluation(userEvaluationDto.getId(),
                userEvaluationDto.getRating(),
                userEvaluationDto.isWatched(),
                userEvaluationDto.getDateWatched(),
                userEvaluationDto.getPlanned(),
                userEvaluationDto.getMovie());
    }

    public UserEvaluationDto mapToUserEvaluationDto(final UserEvaluation userEvaluation) {
        return new UserEvaluationDto(userEvaluation.getId(),
                userEvaluation.getRating(),
                userEvaluation.isWatched(),
                userEvaluation.getDateWatched(),
                userEvaluation.getPlanned(),
                userEvaluation.getMovie());
    }
}
