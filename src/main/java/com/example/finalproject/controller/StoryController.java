package com.example.finalproject.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class StoryController {

    @GetMapping("/story/board")
    public String story() {
        return "boardPages/boardStory";
    }
}
