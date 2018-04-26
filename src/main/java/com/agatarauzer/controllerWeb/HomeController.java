package com.agatarauzer.controllerWeb;

import com.agatarauzer.model.Movie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("movieModel", new Movie());
        return "index";
    }
}
