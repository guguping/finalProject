package com.example.finalproject.controller;

import com.example.finalproject.dto.BoardDTO;
import com.example.finalproject.dto.BoardFileDTO;
import com.example.finalproject.dto.MemberDTO;
import com.example.finalproject.serivce.AxiosService;
import com.example.finalproject.serivce.BoardService;
import com.example.finalproject.serivce.MemberSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AxiosController {
    private final AxiosService axiosService;
    private final BoardService boardService;
    private final MemberSerivce memberSerivce;

    @PostMapping("/member/{inputEmail}/dup-check")
    public ResponseEntity<Boolean> mailCheck(@PathVariable String inputEmail) {
        return ResponseEntity.ok(memberSerivce.mailCheck(inputEmail));
    }

    @PostMapping("/member/{inputNickname}/dupl-check")
    public ResponseEntity<Boolean> nicknameCheck(@PathVariable String inputNickname) {
        return ResponseEntity.ok(memberSerivce.nicknameCheck(inputNickname));
    }

    @GetMapping("/board/{id}")
    public ResponseEntity findById(@PathVariable Long id) throws Exception {
        System.out.println("id" + id);
        BoardDTO boardDTO = boardService.findById(id);
        MemberDTO memberDTO = memberSerivce.findById(boardDTO.getMemberId());
        List<BoardFileDTO> boardFileDTOList = boardService.findBoardFile(boardDTO.getId());
        Map<String, Object> board = new HashMap<>();
        board.put("boardDTO", boardDTO);
        board.put("memberDTO", memberDTO);
        board.put("BoardFileList", boardFileDTOList);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }
}
