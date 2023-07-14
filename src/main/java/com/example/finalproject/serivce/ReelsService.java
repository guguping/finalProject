package com.example.finalproject.serivce;

import com.example.finalproject.dto.*;
import com.example.finalproject.entitiy.*;
import com.example.finalproject.repository.BoardReelsRepository;
import com.example.finalproject.repository.MemberRepository;
import com.example.finalproject.repository.ReelsCommentLikeRepository;
import com.example.finalproject.repository.ReelsCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ReelsService {
    private final BoardReelsRepository boardReelsRepository;
    private final ReelsCommentRepository reelsCommentRepository;
    private final MemberRepository memberRepository;
    private final ReelsCommentLikeRepository reelsCommentLikeRepository;

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
    public Map<String, Object> findByBoardReelsEntityOrderByIdDesc(BoardReelsDTO boardReelsDTO, Long memberId) {
        BoardReelsEntity boardReelsEntity = boardReelsRepository.findById(boardReelsDTO.getId()).orElseThrow(() -> new NoSuchElementException());
        List<BoardReelsCommentEntity> boardReelsCommentEntityList = reelsCommentRepository.findByBoardReelsEntityOrderByIdDesc(boardReelsEntity);
        List<BoardReelsCommentDTO> boardReelsCommentDTOList = new ArrayList<>();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        List<LikeDTO> reelsCommentLikeList = new ArrayList<>();
        Map<String, Object> reelsCommentResponse = new HashMap<>();
        boardReelsCommentEntityList.forEach(boardReelsCommentEntity -> {
            reelsCommentLikeList.add(LikeDTO.reelsCommentLikeToDTO(reelsCommentLikeRepository.findByBoardReelsCommentEntity(boardReelsCommentEntity).orElse(null)));
            boardReelsCommentDTOList.add(BoardReelsCommentDTO.toDTO(boardReelsCommentEntity));
        });
        boardReelsCommentDTOList.forEach(boardReelsCommentDTO -> {
            memberDTOList.add(MemberDTO.toDTO(memberRepository.findById(boardReelsCommentDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException())));
        });
        MemberDTO seeMember = MemberDTO.toDTO(memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException()));
        reelsCommentResponse.put("commentContentsList", boardReelsCommentDTOList);
        reelsCommentResponse.put("commentLikeList", reelsCommentLikeList);
        reelsCommentResponse.put("commentMember", memberDTOList);
        reelsCommentResponse.put("seeMemberDTO", seeMember);
        return reelsCommentResponse;
    }
}
