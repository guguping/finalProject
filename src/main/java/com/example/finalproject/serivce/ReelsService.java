package com.example.finalproject.serivce;

import com.example.finalproject.dto.BoardDTO;
import com.example.finalproject.dto.BoardReelsCommentDTO;
import com.example.finalproject.dto.BoardReelsDTO;
import com.example.finalproject.entitiy.BoardCommentEntity;
import com.example.finalproject.entitiy.BoardLikeEntity;
import com.example.finalproject.entitiy.BoardReelsCommentEntity;
import com.example.finalproject.entitiy.BoardReelsEntity;
import com.example.finalproject.repository.BoardReelsRepository;
import com.example.finalproject.repository.ReelsCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReelsService {
    private final BoardReelsRepository boardReelsRepository;
    private final ReelsCommentRepository reelsCommentRepository;

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
    public List<BoardReelsCommentDTO> findByBoardReelsEntityOrderByIdDesc(BoardReelsDTO boardReelsDTO) {
        BoardReelsEntity boardReelsEntity = boardReelsRepository.findById(boardReelsDTO.getId()).orElseThrow(() -> new NoSuchElementException());
        List<BoardReelsCommentEntity> boardReelsCommentEntityList = reelsCommentRepository.findByBoardReelsEntityOrderByIdDesc(boardReelsEntity);
        List<BoardReelsCommentDTO> boardReelsCommentDTOList = new ArrayList<>();
        boardReelsCommentEntityList.forEach(boardReelsCommentEntity -> {
            boardReelsCommentDTOList.add(BoardReelsCommentDTO.toDTO(boardReelsCommentEntity));
        });
        return boardReelsCommentDTOList;
    }
}
