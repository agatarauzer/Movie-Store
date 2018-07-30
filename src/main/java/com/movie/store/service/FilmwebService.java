package com.movie.store.service;

import com.movie.store.domain.MovieFilmwebDto;
import info.talacha.filmweb.api.FilmwebApi;
import info.talacha.filmweb.connection.FilmwebException;
import info.talacha.filmweb.models.Film;
import info.talacha.filmweb.search.models.FilmSearchResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmwebService {

    private FilmwebApi filmweb = new FilmwebApi();

    public List<MovieFilmwebDto> getFullMovieInfo(String title) throws FilmwebException {
        List<MovieFilmwebDto> movieFilmwebDtos = new ArrayList<>();
        List<FilmSearchResult> searchResults = filmweb.findFilm(title);

        for (FilmSearchResult film : searchResults) {
           Long filmwebId = film.getId();
           Film filmwebFilmData = filmweb.getFilmData(filmwebId);

           MovieFilmwebDto movieFilmwebDto = new MovieFilmwebDto(
                   filmwebId,
                   filmwebFilmData.getPolishTitle(),
                   filmwebFilmData.getTitle(),
                   filmwebFilmData.getYear(),
                   filmwebFilmData.getPosterURL(),
                   filmwebFilmData.getWebsiteURL(),
                   filmwebFilmData.getRate(),
                   filmwebFilmData.getVotes(),
                   filmwebFilmData.getGenre(),
                   filmwebFilmData.getCountries(),
                   filmwebFilmData.getPlot()
           );
           movieFilmwebDtos.add(movieFilmwebDto);
        }
        return movieFilmwebDtos;
    }
}
