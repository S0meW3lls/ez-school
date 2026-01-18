package com.samueldeguio.ezschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoursesController {

    @GetMapping("/courses")
    public String courses() {
        return "pages/courses";
    }
}
