package com.example.jsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class MvcController {
    @GetMapping("/")
    public String index() throws IOException {
        return "index";
    }
}

