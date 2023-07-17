package com.example.finalproject.controller;

import com.example.finalproject.dto.BoardCommentDTO;
import com.example.finalproject.dto.BoardDTO;
import com.example.finalproject.dto.BoardFileDTO;
import com.example.finalproject.dto.MemberDTO;
import com.example.finalproject.serivce.*;
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
public class AxiosController {
    private final AxiosService axiosService;
    private final BoardService boardService;
    private final MemberSerivce memberSerivce;
    private final LikeServie likeServie;
    private final BookmarkService bookmarkService;
    private final BoardCommentService boardCommentService;

    @PostMapping("/member/email-check/{inputEmail}")
    public ResponseEntity<Boolean> mailCheck(@PathVariable String inputEmail) {
        System.out.println(inputEmail);
        boolean isEmailExist = memberSerivce.mailCheck(inputEmail);
        System.out.println(isEmailExist);
        if (inputEmail.length() == 0) {
            return ResponseEntity.ok(false);
        } else if (!inputEmail.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            return ResponseEntity.ok(false);
        } else if (isEmailExist) {
            return ResponseEntity.ok(false);
        } else {
            return ResponseEntity.ok(true);
        }
//        return ResponseEntity.ok(memberSerivce.mailCheck(inputEmail));
    }

    @PostMapping("/member/nickname-check/{inputNickname}")
    public ResponseEntity<Boolean> nicknameCheck(@PathVariable String inputNickname) {
        boolean isNickNameExist = memberSerivce.nicknameCheck(inputNickname);
        if (inputNickname.length() == 0) {
            return ResponseEntity.ok(false);
        } else if (!inputNickname.matches("^(?!.*[._])[\\w.]{4,20}$")) {
            return ResponseEntity.ok(false);
        } else if (isNickNameExist) {
            return ResponseEntity.ok(false);
        } else {
            return ResponseEntity.ok(true);
        }
//        return ResponseEntity.ok(memberSerivce.nicknameCheck(inputNickname));
    }

    @GetMapping("/board/{id}")
    public ResponseEntity findById(@PathVariable Long id, @RequestParam("boardKind") int boardKind, HttpSession session) throws Exception {
        Long loginId = (Long) session.getAttribute("memberId");
        BoardDTO boardDTO = boardService.findById(id);
        MemberDTO memberDTO = memberSerivce.findById(boardDTO.getMemberId());
        boolean likeOk = likeServie.findByBoardLike(id, loginId, boardKind);
        boolean bookmarkOk = bookmarkService.findByBoardBookmark(id, loginId, boardKind);
        List<BoardFileDTO> boardFileDTOList = boardService.findBoardFile(boardDTO.getId());
        List<BoardCommentDTO> boardCommentDTOList = boardCommentService.findAll(boardDTO.getId());
        Map<String, Object> board = new HashMap<>();
        board.put("boardDTO", boardDTO);
        board.put("memberDTO", memberDTO);
        board.put("boardFileList", boardFileDTOList);
        board.put("boardLike", likeOk);
        board.put("boardBookmark", bookmarkOk);
        board.put("boardCommentList", boardCommentDTOList);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @PostMapping("/board/like/{id}")
    public ResponseEntity boardLike(@PathVariable Long id, @RequestParam("boardKind") int boardKind, HttpSession session) {
        Long loginId = (Long) session.getAttribute("memberId");
        likeServie.save(id, loginId, boardKind);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/board/unLike/{id}")
    public ResponseEntity boardUnLike(@PathVariable Long id, @RequestParam("boardKind") int boardKind, HttpSession session) {
        Long loginId = (Long) session.getAttribute("memberId");
        likeServie.delete(id, loginId, boardKind);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/board/bookmark/{id}")
    public ResponseEntity boardBookmark(@PathVariable Long id, @RequestParam("boardKind") int boardKind, HttpSession session) {
        Long loginId = (Long) session.getAttribute("memberId");
        bookmarkService.save(id, loginId, boardKind);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/board/unBookmark/{id}")
    public ResponseEntity boardUnBookmark(@PathVariable Long id, @RequestParam("boardKind") int boardKind, HttpSession session) {
        Long loginId = (Long) session.getAttribute("memberId");
        bookmarkService.delete(id, loginId, boardKind);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/board/comment/{id}")
    public ResponseEntity boardComment(@PathVariable Long id, @RequestBody BoardCommentDTO boardCommentDTO, HttpSession session) {
        Long loginId = (Long) session.getAttribute("memberId");
        boardCommentDTO.setBoardId(id);
        boardCommentDTO.setMemberId(loginId);
        boardCommentService.save(boardCommentDTO);
        List<BoardCommentDTO> boardCommentDTOList = boardCommentService.findAll(boardCommentDTO.getBoardId());
        return new ResponseEntity<>(boardCommentDTOList, HttpStatus.OK);
    }
}
