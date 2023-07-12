package com.example.finalproject.serivce;

import com.example.finalproject.dto.BoardReelsDTO;
import com.example.finalproject.entitiy.BoardReelsEntity;
import com.example.finalproject.repository.BoardReelsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReelsService {
    private final BoardReelsRepository boardReelsRepository;

    @Transactional
    public List<BoardReelsDTO> reelsFindAll() {
        List<BoardReelsEntity> boardReelsEntityList = boardReelsRepository.findAll();
        List<BoardReelsDTO> boardReelsDTOList = new ArrayList<>();
        boardReelsEntityList.forEach(boardReelsEntity -> {
            boardReelsDTOList.add(BoardReelsDTO.toDTO(boardReelsEntity));
        });
        return boardReelsDTOList;
    }
}
