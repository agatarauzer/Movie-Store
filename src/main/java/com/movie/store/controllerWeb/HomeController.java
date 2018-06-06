package com.agatarauzer.controllerWeb;

import com.agatarauzer.model.Movie;
import com.agatarauzer.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    MoviesRepository moviesRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("movie", new Movie());
        return "index";
    }

    @PostMapping("/")
    public String checkForm(@Valid @ModelAttribute("movie") Movie movie, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
            return "index";
        }
        moviesRepository.save(movie);
        return "result";
    }
}



