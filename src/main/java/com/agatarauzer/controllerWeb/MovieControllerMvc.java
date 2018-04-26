package com.agatarauzer.controllerWeb;


import com.agatarauzer.model.Movie;
import com.agatarauzer.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/movie")
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

    @PostMapping
    public String addMovie(@ModelAttribute Movie movieModel, RedirectAttributes redirectAttr) {
        moviesRepo.save(movieModel);
        redirectAttr.addFlashAttribute("message", "Movie added successfully");
        return "redirect:/";
    }
}
