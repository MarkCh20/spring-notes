package com.example.notesapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestHtmlController {

    @GetMapping("/test-html")
    public String showTestHtml() {
        return "test"; // according to test.html
    }
}
