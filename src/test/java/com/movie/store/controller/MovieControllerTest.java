package com.movie.store.controller;

import com.movie.store.domain.*;
import com.movie.store.exception.MovieNotFoundException;
import com.movie.store.mapper.MovieMapper;
import com.movie.store.service.MovieService;
import com.movie.store.validator.MovieGenreValidator;
import com.movie.store.validator.MovieStatusValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieMapper mapper;

    @MockBean
    private MovieService service;

    @MockBean
    private MovieStatusValidator statusValidator;

    @MockBean
    private MovieGenreValidator genreValidator;

    private Movie movie;
    private MovieDto movieDto;
    private List<Movie> movies;
    private List<MovieDto> movieDtos;
    private List<Actor> actors;
    private UserEvaluation userEvaluation;


    @Before
    public void createTestData() {
        actors = new ArrayList<>();
        actors.add(new Actor(1L, "test actor", new ArrayList<>()));

        userEvaluation = new UserEvaluation(1L, 10, "watched", LocalDate.of(1990, 1, 1), "test comment");

        movie = new Movie(1L, "test title", "test director", 1111, "drama", actors, userEvaluation);
        movieDto = new MovieDto(1L, "test title", "test director", 1111, "drama", actors, userEvaluation);

        movies = new ArrayList<>();
        movies.add(movie);

        movieDtos = new ArrayList<>();
        movieDtos.add(movieDto);
    }

    @Test
    public void shouldGetAllMovies() throws Exception {
        //Given
        when(mapper.mapToMovieDtoList(movies)).thenReturn(movieDtos);
        when(service.getAllMovies()).thenReturn(movies);

        //When & Then
        mockMvc.perform(get("/v2/movie-store/movies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("test title")))
                .andExpect(jsonPath("$[0].director", is("test director")))
                .andExpect(jsonPath("$[0].actors[0].name", is("test actor")));
    }

    @Test
    public void shouldGetMovieById() throws Exception {
        //Given
        when(mapper.mapToMovieDto(movie)).thenReturn(movieDto);
        when(service.getMovie(1L)).thenReturn(Optional.of(movie));

        //When & Then
        mockMvc.perform(get("/v2/movie-store/movie/id")
                .param("id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("test title")))
                .andExpect(jsonPath("$.director", is("test director")))
                .andExpect(jsonPath("$.userEvaluation.rating", is(10)))
                .andExpect(jsonPath("$.userEvaluation.status", is("watched")))
                .andExpect(jsonPath("$.actors[0].name", is("test actor")));
    }

    @Test
    public void getNotExistingMovie_shouldThrowException() throws Exception {

        when(service.getMovie(33L)).thenThrow(new MovieNotFoundException());

        mockMvc.perform(get("/v2/movie-store/movie/id")
                .param("id", "33"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldGetMovieByTitle() throws Exception {
        //Given
        when(mapper.mapToMovieDtoList(movies)).thenReturn(movieDtos);
        when(service.getMovies("test title")).thenReturn(movies);

        //When & Then
        mockMvc.perform(get("/v2/movie-store/movie/title")
                .param("title", "test title")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].director", is("test director")))
                .andExpect(jsonPath("$[0].userEvaluation.rating", is(10)))
                .andExpect(jsonPath("$[0].userEvaluation.status", is("watched")))
                .andExpect(jsonPath("$[0].actors[0].name", is("test actor")));
    }

    @Test
    public void shouldAddNewMovie() throws Exception {
        //Given
        when(mapper.mapToMovie(ArgumentMatchers.any(MovieDto.class))).thenReturn(movie);
        when(mapper.mapToMovieDto(ArgumentMatchers.any(Movie.class))).thenReturn(movieDto);
        when(service.saveMovie(movie)).thenReturn(movie);

        //When & Then
        mockMvc.perform(post("/v2/movie-store/movie")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(ToJsonConverter.convertObjectToJsonBytes(movieDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("test title")))
                .andExpect(jsonPath("$.director", is("test director")))
                .andExpect(jsonPath("$.userEvaluation.rating", is(10)));
    }

    @Test
    public void shouldGetWatchedMovies() throws Exception {
        //Given
        when(service.getAllWatchedMovies()).thenReturn(movies);
        when(mapper.mapToMovieDtoList(movies)).thenReturn(movieDtos);

        //When & Then
        mockMvc.perform(get("/v2/movie-store/movies/watched")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].director", is("test director")))
                .andExpect(jsonPath("$[0].userEvaluation.rating", is(10)))
                .andExpect(jsonPath("$[0].userEvaluation.status", is("watched")))
                .andExpect(jsonPath("$[0].actors[0].name", is("test actor")));
    }

    @Test
    public void getWatchedMovies_shouldReturnEmptyList() throws Exception {
        //Given
        userEvaluation.setStatus("planned");
        when(service.getAllWatchedMovies()).thenReturn(new ArrayList<>());

        //When & Then
        mockMvc.perform(get("/v2/movie-store/movies/watched")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldGetPlannedMovies() throws Exception{
        //Given
        userEvaluation.setStatus("planned");
        when(service.getAllMoviesPlannedToWatch()).thenReturn(movies);
        when(mapper.mapToMovieDtoList(movies)).thenReturn(movieDtos);

        //When & Then
        mockMvc.perform(get("/v2/movie-store/movies/planned")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].director", is("test director")))
                .andExpect(jsonPath("$[0].userEvaluation.rating", is(10)))
                .andExpect(jsonPath("$[0].userEvaluation.status", is("planned")))
                .andExpect(jsonPath("$[0].actors[0].name", is("test actor")));
    }

    @Test
    public void shouldGetMoviesWithRating10() throws Exception {
        //Given
        when(service.getAllMoviesWithRating(10)).thenReturn(movies);
        when(mapper.mapToMovieDtoList(movies)).thenReturn(movieDtos);

        //When & Then
        mockMvc.perform(get("/v2/movie-store/movies/rating")
                .contentType(MediaType.APPLICATION_JSON)
                .param("rating", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].director", is("test director")))
                .andExpect(jsonPath("$[0].userEvaluation.rating", is(10)))
                .andExpect(jsonPath("$[0].userEvaluation.status", is("watched")))
                .andExpect(jsonPath("$[0].actors[0].name", is("test actor")));
    }

    @Test
    public void shouldUpdateMovie() throws Exception {
        //Given
        userEvaluation.setComment("la la la");
        when(mapper.mapToMovie(ArgumentMatchers.any(MovieDto.class))).thenReturn(movie);
        when(mapper.mapToMovieDto(ArgumentMatchers.any(Movie.class))).thenReturn(movieDto);
        when(service.saveMovie(movie)).thenReturn(movie);

        //When & Then
        mockMvc.perform(put("/v2/movie-store/movie")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(ToJsonConverter.convertObjectToJsonBytes(movieDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("test title")))
                .andExpect(jsonPath("$.director", is("test director")))
                .andExpect(jsonPath("$.userEvaluation.comment", is("la la la")));
    }
}