package com.example.finalproject.controller;

import com.example.finalproject.serivce.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
}
