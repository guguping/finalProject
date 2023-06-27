package com.example.finalproject.controller;

import com.example.finalproject.dto.BoardDTO;
import com.example.finalproject.serivce.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @GetMapping("/board/main")
    public String boardMain(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "boardPages/boardMain";
    }

    @PostMapping("/board/save")
    public String save(@ModelAttribute BoardDTO boardDTO) throws IOException{
        boardService.save(boardDTO);
        return "redirect:/board/main";
    }

}
