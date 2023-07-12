package com.example.finalproject.controller;

import com.example.finalproject.dto.*;
import com.example.finalproject.entitiy.MemberEntity;
import com.example.finalproject.repository.MemberFollowRepository;
import com.example.finalproject.serivce.BoardCommentService;
import com.example.finalproject.serivce.BoardService;
import com.example.finalproject.serivce.MemberFollowService;
import com.example.finalproject.serivce.MemberSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final MemberSerivce memberSerivce;
    private final MemberFollowService memberFollowService;

    private final BoardCommentService boardCommentService;

    @GetMapping("/board/main")
    public String boardMain(Model model, HttpSession session) {
//        ,@PathVariable Long id
        Long loginId = (Long) session.getAttribute("memberId");
        MemberDTO memberDTO = memberSerivce.findById(loginId);
        List<BoardDTO> boardDTOList = boardService.findAll(loginId);
        List<BoardFileDTO> boardFileDTOList = boardService.findAllFile();
        List<MemberDTO> memberDTOList = memberSerivce.findAll();
//        List<BoardCommentDTO> boardCommentDTOList = boardCommentService.findAll();
        BoardDTO boardDTO = boardService.findById(loginId);
        List<BoardCommentDTO> boardCommentDTOList = boardCommentService.findAll(boardDTO.getId());
//        boardDTOList.forEach(boardDTO -> {
//
//        });

        model.addAttribute("boardCommentDTOList", boardCommentDTOList);
        model.addAttribute("boardFileList",boardFileDTOList);
        model.addAttribute("memberDTOList",memberDTOList);
        model.addAttribute("boardDTOList", boardDTOList);
        model.addAttribute("memberDTO",memberDTO);

        return "boardPages/boardMain";
    }

    @PostMapping("/board/save")
    public String save(@ModelAttribute BoardDTO boardDTO, HttpSession session) throws IOException{
        Long loginId = (Long) session.getAttribute("memberId");
        boardDTO.setMemberId(loginId);
        boardService.save(boardDTO);
        return "redirect:/board/main";
    }

}
