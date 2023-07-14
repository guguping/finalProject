package com.example.finalproject.serivce;

import com.example.finalproject.dto.BoardDTO;
import com.example.finalproject.dto.BoardReelsCommentDTO;
import com.example.finalproject.dto.BoardReelsDTO;
import com.example.finalproject.dto.MemberDTO;
import com.example.finalproject.entitiy.*;
import com.example.finalproject.repository.BoardReelsRepository;
import com.example.finalproject.repository.MemberRepository;
import com.example.finalproject.repository.ReelsCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ReelsService {
    private final BoardReelsRepository boardReelsRepository;
    private final ReelsCommentRepository reelsCommentRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public List<BoardReelsDTO> reelsFindAll() {
        List<BoardReelsEntity> boardReelsEntityList = boardReelsRepository.findAll();
        List<BoardReelsDTO> boardReelsDTOList = new ArrayList<>();
        boardReelsEntityList.forEach(boardReelsEntity -> {
            boardReelsDTOList.add(BoardReelsDTO.toDTO(boardReelsEntity));
        });
        return boardReelsDTOList;
    }

    @Transactional
    public Map<String, Object> findByBoardReelsEntityOrderByIdDesc(BoardReelsDTO boardReelsDTO) {
        BoardReelsEntity boardReelsEntity = boardReelsRepository.findById(boardReelsDTO.getId()).orElseThrow(() -> new NoSuchElementException());
        List<BoardReelsCommentEntity> boardReelsCommentEntityList = reelsCommentRepository.findByBoardReelsEntityOrderByIdDesc(boardReelsEntity);
        List<BoardReelsCommentDTO> boardReelsCommentDTOList = new ArrayList<>();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        Map<String, Object> reelsCommentResponse = new HashMap<>();
        boardReelsCommentEntityList.forEach(boardReelsCommentEntity -> {
            boardReelsCommentDTOList.add(BoardReelsCommentDTO.toDTO(boardReelsCommentEntity));
        });
        boardReelsCommentDTOList.forEach(boardReelsCommentDTO -> {
            memberDTOList.add(MemberDTO.toDTO(memberRepository.findById(boardReelsCommentDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException())));
        });
        reelsCommentResponse.put("commentContentsList", boardReelsCommentDTOList);
        reelsCommentResponse.put("commentMember", memberDTOList);
        return reelsCommentResponse;
    }
}
