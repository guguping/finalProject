package com.example.finalproject.dto;

import com.example.finalproject.entitiy.BoardReelsCommentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardReelsCommentDTO {
    private Long id;
    private Long reelsId;
    private Long memberId;
    private String commentContents;
    private String createdAt;

    //     0 = 비수정 / 1 = 수정
    private int commentUpdate = 0;

    public static BoardReelsCommentDTO toDTO(BoardReelsCommentEntity boardReelsCommentEntity) {
        BoardReelsCommentDTO boardReelsCommentDTO = new BoardReelsCommentDTO();
        boardReelsCommentDTO.setId(boardReelsCommentEntity.getId());
        boardReelsCommentDTO.setReelsId(boardReelsCommentEntity.getBoardReelsEntity().getId());
        boardReelsCommentDTO.setMemberId(boardReelsCommentEntity.getMemberEntity().getId());
        return boardReelsCommentDTO;
    }
}
