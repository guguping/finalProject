package com.example.finalproject.controller;
import com.example.finalproject.dto.BoardCommentDTO;
import com.example.finalproject.dto.MemberDTO;
import com.example.finalproject.serivce.BoardCommentService;
import com.example.finalproject.serivce.MemberSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequiredArgsConstructor
public class BoardCommentController {
    private final BoardCommentService boardCommentService;
    private final MemberSerivce memberSerivce;

        @PostMapping("/comment/save")
    public ResponseEntity save(@RequestBody BoardCommentDTO boardCommentDTO, HttpSession session) {
        Long loginId = (Long) session.getAttribute("memberId");
        MemberDTO memberDTO = memberSerivce.findById(loginId);

        System.out.println("boardCommentDTO = " + boardCommentDTO);
//        try {
            boardCommentService.save(boardCommentDTO);
            List<BoardCommentDTO> commentDTOList = boardCommentService.findAll(boardCommentDTO.getBoardId());
            Map<String, Object> boardMain = new HashMap<>();
            boardMain.put("commentDTOList", commentDTOList);
            boardMain.put("memberDTO", memberDTO);
            return new ResponseEntity<>(boardMain, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        boardCommentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

