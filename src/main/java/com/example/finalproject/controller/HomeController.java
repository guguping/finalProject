package com.example.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor // 생성자 주입에 서용
public class HomeController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
