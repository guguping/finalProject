package com.example.finalproject.controller;

import com.example.finalproject.dto.*;
import com.example.finalproject.serivce.MemberSerivce;
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
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
public class ReelsController {
    private final ReelsService reelsService;
    private final MemberSerivce memberSerivce;

    @GetMapping("/board/reels")
    public String reelsPage(Model model, HttpSession session) {
        List<BoardReelsDTO> boardReelsDTOList = reelsService.reelsFindAll((Long) session.getAttribute("memberId"));
        MemberDTO memberDTO = memberSerivce.findById((Long) session.getAttribute("memberId"));
        if (boardReelsDTOList == null) {
            model.addAttribute("boardReelsList", null);
            model.addAttribute("commentCount",null);
            model.addAttribute("likeCount", null);
        } else {
            List<Integer> commentCount = reelsService.commentCount(boardReelsDTOList);
            List<Integer> likeCount = reelsService.likeCount(boardReelsDTOList);
            model.addAttribute("memberDTO",memberDTO);
            model.addAttribute("boardReelsList", boardReelsDTOList);
            model.addAttribute("commentCount",commentCount);
            model.addAttribute("likeCount",likeCount);
        }
        return "boardPages/boardReels";
    }

    @PostMapping("/Reels/comment")
    public ResponseEntity<?> reelsComment(@RequestBody BoardReelsDTO boardReelsDTO,
                                          Model model, HttpSession httpSession) {
        Map<String, Object> reelsCommentResponse = reelsService.findByBoardReelsEntityOrderByIdDesc(boardReelsDTO, (Long) httpSession.getAttribute("memberId"));

        return new ResponseEntity<>(reelsCommentResponse, HttpStatus.OK);
    }

    @PostMapping("/Reels/commentSave")
    public ResponseEntity<?> reelsCommentSave(@RequestBody BoardReelsCommentDTO boardReelsCommentDTO,
                                              HttpSession session) {
        boardReelsCommentDTO.setMemberId((Long) session.getAttribute("memberId"));
        reelsService.save(boardReelsCommentDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/Reels/commentLike")
    public ResponseEntity<?> reelsCommentLike(@RequestBody LikeDTO likeDTO) {
        LikeDTO likeDTOResult = reelsService.saveLike(likeDTO);
        return new ResponseEntity<>(likeDTOResult, HttpStatus.OK);
    }

    @PostMapping("/Reels/commentUnLike")
    public ResponseEntity<?> reelsCommentUnLike(@RequestBody LikeDTO likeDTO) {
        reelsService.deleteLike(likeDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/Reels/likeSave")
    public ResponseEntity<?> reelsLikeSave(@RequestBody LikeDTO likeDTO) {
        LikeDTO likeDTOResult = reelsService.saveReelsLike(likeDTO);
        return new ResponseEntity<>(likeDTOResult, HttpStatus.OK);
    }

    @PostMapping("/Reels/likeDelete")
    public ResponseEntity<?> reelsLikeDelete(@RequestBody LikeDTO likeDTO) {
        reelsService.deleteLike(likeDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/Reels/markSave")
    public ResponseEntity<?> bookMarkSave(@RequestBody BookmarkDTO bookmarkDTO) {
        BookmarkDTO bookmarkDTOResult = reelsService.saveBookMark(bookmarkDTO);
        return new ResponseEntity<>(bookmarkDTOResult, HttpStatus.OK);
    }
    @PostMapping("/Reels/markDelete")
    public ResponseEntity<?> bookMarkDelete(@RequestBody BookmarkDTO bookmarkDTO) {
        System.out.println("bookmarkDTO = " + bookmarkDTO);
        reelsService.deleteBook(bookmarkDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
