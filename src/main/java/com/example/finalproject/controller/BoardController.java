package com.example.finalproject.controller;

import com.example.finalproject.dto.*;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final MemberSerivce memberSerivce;
    private final MemberFollowService memberFollowService;

    @GetMapping("/board/main")
    public String boardMain(Model model,HttpSession session) {
        Long loginId = (Long) session.getAttribute("memberId");
        MemberDTO memberDTO = memberSerivce.findById(loginId);
        List<MemberFollowDTO> memberFollowDTOList = memberFollowService.findFollow(loginId);
        List<BoardDTO> boardDTOList = boardService.findAll();
        List<BoardFileDTO> boardFileDTOList = boardService.findAllFile();
        List<MemberDTO> memberDTOList = memberSerivce.findAll();
        BoardDTO boardDTO = new BoardDTO();
//        Long memberId = boardDTO.getMemberId();
////        if (memberId.equals(loginId)) {
////            // 작성자와 로그인한 사용자가 같은 경우, ... 메뉴 버튼을 보여줌
////            model.addAttribute("isAuthor", true);
////        } else {
////            // 작성자와 로그인한 사용자가 다른 경우, ... 메뉴 버튼을 보여주지 않음
////            model.addAttribute("isAuthor", false);
////        }
////        for (MemberFollowDTO memberFollowDTO : memberFollowDTOList) {
////            Long followedMemberId = memberFollowDTO.getFollowingId();
////            List<BoardDTO> followedMemberPosts = boardService.findByMemberId(followedMemberId);
////            boardDTOList.addAll(followedMemberPosts);
////        }

//        model.addAttribute("boardFileList",boardFileDTOList);
        model.addAttribute("memberDTOList",memberDTOList);
        model.addAttribute("boardDTOList", boardDTOList);
        model.addAttribute("memberDTO",memberDTO);
        model.addAttribute("memberFollowList", memberFollowDTOList);
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
