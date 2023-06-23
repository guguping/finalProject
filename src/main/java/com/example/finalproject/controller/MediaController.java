package com.example.finalproject.controller;

import com.example.finalproject.serivce.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MediaController {
    private final MediaService mediaService;
}
