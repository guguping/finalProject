package com.example.finalproject.controller;

import com.example.finalproject.dto.BoardDTO;
import com.example.finalproject.dto.MemberDTO;
import com.example.finalproject.dto.MemberFollowDTO;
import com.example.finalproject.entitiy.MemberEntity;
import com.example.finalproject.repository.MemberFollowRepository;
import com.example.finalproject.serivce.BoardService;
import com.example.finalproject.serivce.MemberFollowService;
import com.example.finalproject.serivce.MemberSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final MemberSerivce memberSerivce;
    private final MemberFollowRepository memberFollowRepository;

    private final MemberFollowService memberFollowService;

    @GetMapping("/board/main")
    public String boardMain(Model model,HttpSession session) {
        List<BoardDTO> boardDTOList = boardService.findAll();
        Long loginId = (Long) session.getAttribute("memberId");
        List<MemberFollowDTO> memberFollowDTOList = memberFollowService.findFollow(loginId);
        model.addAttribute("boardList", boardDTOList);
        model.addAttribute("memberFollowList", memberFollowDTOList);
        return "boardPages/boardMain";
    }

    @PostMapping("/board/save")
    public String save(@ModelAttribute BoardDTO boardDTO) throws IOException{
        boardService.save(boardDTO);
        return "redirect:/board/main";
    }

}
