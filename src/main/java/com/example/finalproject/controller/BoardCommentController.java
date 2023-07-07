package com.example.finalproject.controller;
import com.example.finalproject.dto.BoardCommentDTO;
import com.example.finalproject.serivce.BoardCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class BoardCommentController {
    private final BoardCommentService boardCommentService;

    @PostMapping("/comment/save")
    public ResponseEntity save(@RequestBody BoardCommentDTO boardCommentDTO) {
        System.out.println("boardCommentDTO = " + boardCommentDTO);
//        try {
            boardCommentService.save(boardCommentDTO);
            List<BoardCommentDTO> commentDTOList = boardCommentService.findAll(boardCommentDTO.getBoardId());
            return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
    }
}
