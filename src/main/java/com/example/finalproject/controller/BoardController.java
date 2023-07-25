package com.example.finalproject.controller;

import com.example.finalproject.dto.*;
import com.example.finalproject.entitiy.MemberEntity;
import com.example.finalproject.repository.MemberFollowRepository;
import com.example.finalproject.serivce.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    private final LikeServie likeServie;
    private final BookmarkService bookmarkService;

    @GetMapping("/board/main")
    public String boardMain(Model model, HttpSession session) {
//        ,@PathVariable Long id
        Long loginId = (Long) session.getAttribute("memberId");
        MemberDTO memberDTO = memberSerivce.findById(loginId);
        List<BoardDTO> boardDTOList = boardService.findAll(loginId);
        List<BoardFileDTO> boardFileDTOList = boardService.findAllFile();
        List<MemberDTO> memberDTOList = memberSerivce.findAll();
        List<BoardCommentDTO> boardCommentDTOList = boardCommentService.findAll();
        BoardDTO boardDTO = boardService.findById(loginId);
//        List<BoardCommentDTO> boardCommentDTOList = boardCommentService.findAll(boardDTO.getId());


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
    @PostMapping("/findById")
    @ResponseBody
    public ResponseEntity<?> findBoardById(@RequestBody Long boardId) {
        BoardDTO boardDTO = boardService.findById(boardId);
        return ResponseEntity.ok(boardDTO);
    }
    @DeleteMapping("/board/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        boardService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/boardDetail/{id}")
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
//    @PutMapping("/boardUpdate/{id}")
//    public ResponseEntity update(@PathVariable Long id, @RequestBody BoardDTO boardDTO) {
//        // 클라이언트로부터 받은 id 값을 사용하여 해당 게시글을 식별합니다.
//        boardDTO.setId(id);
//        // boardService의 update 메서드를 호출하여 해당 게시글을 업데이트합니다.
//        boardService.update(id);
//        // 업데이트가 성공적으로 완료되면 200 OK를 응답으로 반환합니다.
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
@PutMapping("/boardUpdate/{id}")
public ResponseEntity<String> updateBoard(@PathVariable Long id, @RequestBody BoardDTO boardDTO) {
    // BoardDTO에 넘어온 id를 설정합니다.

    try {
        boardService.update(boardDTO);
        return ResponseEntity.ok("게시글 수정 성공!");
    } catch (NoSuchElementException e) {
        return ResponseEntity.notFound().build();
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 수정 실패!");
    }
}


}
