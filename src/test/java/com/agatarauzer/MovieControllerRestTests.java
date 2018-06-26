/*package com.agatarauzer;

import com.agatarauzer.controllerRest.MovieControllerRest;
import com.movie.store.model.Movie;
import com.agatarauzer.repository.MoviesRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieControllerRestTests {

    private MockMvc mockMvc;

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private MovieControllerRest movieControllerRest;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(movieControllerRest)
                .build();
    }

    @After
    public void clear() {
        moviesRepository.deleteAllInBatch();
    }

    @Test
    public void testGetAllMovies_success() throws Exception {
        Movie movie1 = new Movie("Gladiator", "Ridley Scott", 2000);
        Movie movie2 = new Movie("Catch me if you can", "Steven Spielberg", 2002);
        moviesRepository.save(movie1);
        moviesRepository.save(movie2);

        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[0].title", is("Gladiator")))
                .andExpect(jsonPath("$[1].title", is("Catch me if you can")))
                .andExpect(jsonPath("$[0].director", is("Ridley Scott")));
    }

    @Test
    public void testGetAllMovies_emptyList() throws Exception {
        mockMvc.perform(get("/movies"))
                .andExpect(status().isNoContent());

    }

    @Test
    public void testGetMovieById_success() throws Exception {
        Movie movie1 = new Movie("Gladiator", "Ridley Scott", 2000);
        moviesRepository.save(movie1);

        mockMvc.perform(get("/search/{id}", 1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("id").value(1));
    }


    @Test
    public void testGetMoviesByTitle_success() throws Exception {
        Movie movie1 = new Movie("Gladiator", "Ridley Scott", 2000);
        moviesRepository.save(movie1);

        mockMvc.perform(get("/search/{title}", 1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("title").value("Gladiator"));
    }
}

*/