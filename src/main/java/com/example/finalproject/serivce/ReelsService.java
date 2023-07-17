package com.example.finalproject.serivce;

import com.example.finalproject.dto.*;
import com.example.finalproject.entitiy.*;
import com.example.finalproject.repository.*;
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
    private final BoardReelsLikeRepository boardReelsLikeRepository;

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

    public void save(BoardReelsCommentDTO boardReelsCommentDTO) {
        BoardReelsEntity boardReelsEntity = boardReelsRepository.findById(boardReelsCommentDTO.getReelsId()).orElseThrow(() -> new NoSuchElementException());
        MemberEntity memberEntity = memberRepository.findById(boardReelsCommentDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        BoardReelsCommentEntity boardReelsCommentEntity = BoardReelsCommentEntity.toSaveEntity(boardReelsEntity,memberEntity, boardReelsCommentDTO);
        reelsCommentRepository.save(boardReelsCommentEntity);
    }

    public LikeDTO saveLike(LikeDTO likeDTO) {
        BoardReelsCommentEntity boardReelsCommentEntity = reelsCommentRepository.findById(likeDTO.getCommentId()).orElseThrow(() -> new NoSuchElementException());
        MemberEntity memberEntity = memberRepository.findById(likeDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        BoardReelsCommentLikeEntity boardReelsCommentLikeEntity = BoardReelsCommentLikeEntity.toSaveEntity(boardReelsCommentEntity,memberEntity);
        return LikeDTO.reelsCommentLikeToDTO(reelsCommentLikeRepository.save(boardReelsCommentLikeEntity));
    }

    public void deleteLike(LikeDTO likeDTO) {
        if (likeDTO.getCommentId() != null) {
            reelsCommentLikeRepository.deleteById(likeDTO.getId());
        } else {
            boardReelsLikeRepository.deleteById(likeDTO.getId());
        }
    }

    @Transactional
    public List<LikeDTO> findByLike(Long memberId) {
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException());
        List<BoardReelsLikeEntity> boardReelsLikeEntityList = boardReelsLikeRepository.findAllByMemberEntity(memberEntity);
        List<LikeDTO> likeDTOList = new ArrayList<>();
        if (boardReelsLikeEntityList.size() == 0) {
            return null;
        } else {
            boardReelsLikeEntityList.forEach(boardReelsLikeEntity -> {
                likeDTOList.add(LikeDTO.reelsLiketoDTO(boardReelsLikeEntity));
            });
            return likeDTOList;
        }
    }

    @Transactional
    public LikeDTO saveReelsLike(LikeDTO likeDTO) {
        BoardReelsEntity boardReelsEntity = boardReelsRepository.findById(likeDTO.getBoardId()).orElseThrow(() -> new NoSuchElementException());
        MemberEntity memberEntity = memberRepository.findById(likeDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        BoardReelsLikeEntity boardReelsLikeEntity = BoardReelsLikeEntity.toSaveEntity(boardReelsEntity,memberEntity);
        return LikeDTO.reelsLiketoDTO((boardReelsLikeRepository.save(boardReelsLikeEntity)));
    }
}
