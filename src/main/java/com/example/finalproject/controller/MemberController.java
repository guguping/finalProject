package com.example.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    @GetMapping("/member/save")
    public String memberSave() {
        return "memberPages/memberSave";
    }
}
