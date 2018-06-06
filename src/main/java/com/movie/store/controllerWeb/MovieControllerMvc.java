
package com.agatarauzer.controllerWeb;

import com.agatarauzer.model.Movie;
import com.agatarauzer.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieControllerMvc {
    private MoviesRepository moviesRepo;

    @Autowired
    public MovieControllerMvc(MoviesRepository moviesRepo) {
        this.moviesRepo = moviesRepo;
    }

    @GetMapping
    public String listMovies(Model model) {
        List<Movie> movies = moviesRepo.findAll();
        model.addAttribute("movieList", movies);
        return "list";
    }
}

