package com.example.finalproject.serivce;

import com.example.finalproject.dto.*;
import com.example.finalproject.entitiy.*;
import com.example.finalproject.repository.*;
import com.example.finalproject.util.UtilClass;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ReelsService {
    private final BoardReelsRepository boardReelsRepository;
    private final ReelsCommentRepository reelsCommentRepository;
    private final MemberRepository memberRepository;
    private final ReelsCommentLikeRepository reelsCommentLikeRepository;
    private final BoardReelsLikeRepository boardReelsLikeRepository;
    private final BoardReelsBookMarkRepository boardReelsBookMarkRepository;
    private final UtilClass utilClass;

    @Transactional
    public List<BoardReelsDTO> reelsFindAll(Long memberId) {
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException());
        List<BoardReelsEntity> boardReelsEntityList = boardReelsRepository.findAll();
        List<BoardReelsLikeEntity> boardReelsLikeEntityList = boardReelsLikeRepository.findAllByMemberEntityOrderByBoardReelsEntityIdAsc(memberEntity);
        List<BoardReelsBookmarkEntity> boardReelsBookmarkEntityList = boardReelsBookMarkRepository.findAllByMemberEntityOrderByBoardReelsEntityIdAsc(memberEntity);
        List<BoardReelsDTO> boardReelsDTOList = new ArrayList<>();
        for (int i = 0; i < boardReelsEntityList.size(); i++) {
            BoardReelsEntity boardReelsEntity = boardReelsEntityList.get(i);
            BoardReelsLikeEntity boardReelsLikeEntity = null;
            BoardReelsBookmarkEntity boardReelsBookmarkEntity = null;
            BoardReelsDTO dto = new BoardReelsDTO();

            for (int j = 0 ; j < boardReelsLikeEntityList.size(); j++){
                boardReelsLikeEntity = boardReelsLikeEntityList.get(j);
                if (boardReelsLikeEntity != null){
                    if (boardReelsLikeEntity.getBoardReelsEntity().getId() == boardReelsEntity.getId()){
                        dto.setLikeId(boardReelsLikeEntity.getId());
                    }
                }
            }
            for (int q = 0; q < boardReelsBookmarkEntityList.size(); q++) {
                boardReelsBookmarkEntity = boardReelsBookmarkEntityList.get(q);
                if (boardReelsBookmarkEntity != null) {
                    if (boardReelsBookmarkEntity.getBoardReelsEntity().getId() == boardReelsEntity.getId()){
                        dto.setBookId(boardReelsBookmarkEntity.getId());
                    }
                }
            }

            dto.setId(boardReelsEntity.getId());
            dto.setMemberId(boardReelsEntity.getMemberEntity().getId());
            dto.setReelsFile(boardReelsEntity.getReelsFile());
            dto.setReelsContents(boardReelsEntity.getReelsContents());
            dto.setMemberProfile(boardReelsEntity.getMemberEntity().getMemberProfile());
            dto.setMemberNickName(boardReelsEntity.getMemberEntity().getMemberNickName());

            boardReelsDTOList.add(dto);
        }
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
            LocalDateTime createdAt = boardReelsCommentEntity.getCreatedAt();
            String time = utilClass.timeForToday(createdAt);
            boardReelsCommentDTOList.add(BoardReelsCommentDTO.toDTO2(boardReelsCommentEntity, time));
            System.out.println("boardReelsCommentDTOList.get(0).getTimeAgo() = " + boardReelsCommentDTOList.get(0).getTimeAgo());
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
    public LikeDTO saveReelsLike(LikeDTO likeDTO) {
        BoardReelsEntity boardReelsEntity = boardReelsRepository.findById(likeDTO.getBoardId()).orElseThrow(() -> new NoSuchElementException());
        MemberEntity memberEntity = memberRepository.findById(likeDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        BoardReelsLikeEntity boardReelsLikeEntity = BoardReelsLikeEntity.toSaveEntity(boardReelsEntity,memberEntity);
        return LikeDTO.reelsLiketoDTO((boardReelsLikeRepository.save(boardReelsLikeEntity)));
    }

    @Transactional
    public List<Integer> commentCount(List<BoardReelsDTO> boardReelsDTOList) {
        List<Integer> commentCount = new ArrayList<>();
        boardReelsDTOList.forEach(boardReelsDTO -> {
            BoardReelsEntity boardReelsEntity = boardReelsRepository.findById(boardReelsDTO.getId()).orElseThrow(() -> new NoSuchElementException());
            List<BoardReelsCommentEntity> boardReelsCommentEntity = reelsCommentRepository.findByBoardReelsEntityOrderByIdDesc(boardReelsEntity);
            commentCount.add(boardReelsCommentEntity.size());
        });
        return commentCount;
    }

    public List<Integer> likeCount(List<BoardReelsDTO> boardReelsDTOList) {
        List<Integer> likeCount = new ArrayList<>();
        boardReelsDTOList.forEach(boardReelsDTO -> {
            BoardReelsEntity boardReelsEntity = boardReelsRepository.findById(boardReelsDTO.getId()).orElseThrow(() -> new NoSuchElementException());
            List<BoardReelsLikeEntity> boardReelsLikeEntityList = boardReelsLikeRepository.findByBoardReelsEntityOrderByIdDesc(boardReelsEntity);
            likeCount.add(boardReelsLikeEntityList.size());
        });
        return likeCount;
    }

    public BookmarkDTO saveBookMark(BookmarkDTO bookmarkDTO) {
        BoardReelsEntity boardReelsEntity = boardReelsRepository.findById(bookmarkDTO.getReelsId()).orElseThrow(() -> new NoSuchElementException());
        MemberEntity memberEntity = memberRepository.findById(bookmarkDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        BoardReelsBookmarkEntity boardReelsBookmarkEntity = BoardReelsBookmarkEntity.toSavedEntity(boardReelsEntity, memberEntity);
        return BookmarkDTO.reelsToDTO(boardReelsBookMarkRepository.save(boardReelsBookmarkEntity));
    }

    public void deleteBook(BookmarkDTO bookmarkDTO) {
        boardReelsBookMarkRepository.deleteById(bookmarkDTO.getId());
    }
}
