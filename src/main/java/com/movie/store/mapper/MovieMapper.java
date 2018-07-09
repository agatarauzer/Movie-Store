package com.movie.store.mapper;

import com.movie.store.domain.Movie;
import com.movie.store.domain.MovieDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapper {
    public Movie mapToMovie(final MovieDto movieDto) {
        return new Movie(
                movieDto.getId(),
                movieDto.getTitle(),
                movieDto.getDirector(),
                movieDto.getYear(),
                movieDto.getGenre(),
                movieDto.getActors(),
                movieDto.getUserEvaluation()
        );
    }

    public MovieDto mapToMovieDto(final Movie movie) {
        return new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getYear(),
                movie.getGenre(),
                movie.getActors(),
                movie.getUserEvaluation()
        );
    }

    public List<MovieDto> mapToMovieDtoList(final List<Movie> movieList) {
        return movieList.stream()
                .map(movie -> new MovieDto(movie.getId(), movie.getTitle(), movie.getDirector(),
                        movie.getYear(), movie.getGenre(), movie.getActors(), movie.getUserEvaluation()))
                .collect(Collectors.toList());
    }
}
