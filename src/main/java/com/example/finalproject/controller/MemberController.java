package com.example.finalproject.controller;

import com.example.finalproject.serivce.MemberSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberSerivce memberSerivce;

    @GetMapping("/member/save")
    public String memberSave() {
        return "memberPages/memberSave";
    }
}
