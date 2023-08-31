package pl.edu.wszib.jwd.recipebook.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(Model model){
    model.addAttribute("firstName", "Ewa");
    return "homePage";
    }
}
