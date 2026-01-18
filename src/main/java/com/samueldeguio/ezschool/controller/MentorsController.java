package com.samueldeguio.ezschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MentorsController {

    @GetMapping("/mentors")
    public String mentors() {
        return "pages/mentors";
    }
}
