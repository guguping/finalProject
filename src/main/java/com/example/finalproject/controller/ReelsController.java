package com.example.finalproject.controller;

import com.example.finalproject.dto.BoardReelsCommentDTO;
import com.example.finalproject.dto.BoardReelsDTO;
import com.example.finalproject.serivce.ReelsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @PostMapping("/Reels/comment")
    public ResponseEntity reelsComment(@RequestBody BoardReelsDTO boardReelsDTO,
                                       Model model, HttpSession httpSession) {
        System.out.println("boardReelsDTO = " + boardReelsDTO);
        Map<String, Object> reelsCommentResponse = reelsService.findByBoardReelsEntityOrderByIdDesc(boardReelsDTO , (Long) httpSession.getAttribute("memberId"));

        return new ResponseEntity<>(reelsCommentResponse , HttpStatus.OK);
    }
}
