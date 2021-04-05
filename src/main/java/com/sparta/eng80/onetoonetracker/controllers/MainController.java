package com.sparta.eng80.onetoonetracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/competencies")
    public String competencies() {
        return "/fragments/competencies";
    }
}
