package com.example.projecttool.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


// Magnus

@Controller
public class IndexController {
    @GetMapping("home")
    public String homePage() {
        return "index";
    }

    @GetMapping("/")
    public String homePage2() {
        return "redirect:home";
    }

}
