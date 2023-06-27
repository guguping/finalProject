package com.example.finalproject.controller;

import com.example.finalproject.serivce.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @GetMapping("/board/main")
    public String boardMain() {
        return "boardPages/boardMain";
    }
}
