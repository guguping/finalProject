package com.example.finalproject.controller;

import com.example.finalproject.serivce.AxiosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AxiosController {
    private final AxiosService axiosService;
}
