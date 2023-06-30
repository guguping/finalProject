package com.example.finalproject.dto;

import com.example.finalproject.entitiy.BoardFileEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardFileDTO {
    private Long id;
    private Long boardId;
    private String storedFileName;

    public static BoardFileDTO toDTO(BoardFileEntity boardFileEntity) {
        BoardFileDTO boardFileDTO = new BoardFileDTO();
        boardFileDTO.setId(boardFileEntity.getId());
        boardFileDTO.setBoardId(boardFileEntity.getBoardEntity().getId());
        boardFileDTO.setStoredFileName(boardFileEntity.getStoredFileName());
        return boardFileDTO;
    }
}
