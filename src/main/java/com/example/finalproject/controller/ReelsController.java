package com.example.finalproject.controller;

import com.example.finalproject.dto.BoardReelsDTO;
import com.example.finalproject.serivce.ReelsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReelsController {
    private final ReelsService reelsService;
    @GetMapping("/board/reels")
    public String reelsPage(Model model , HttpSession session) {
        List<BoardReelsDTO> boardReelsDTOList = reelsService.reelsFindAll();
        if (boardReelsDTOList == null) {
            model.addAttribute("boardReelsList",null);
        } else {
            model.addAttribute("boardReelsList",boardReelsDTOList);
        }
        return "boardPages/boardReels";
    }
}
