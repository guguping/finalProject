package com.example.finalproject.dto;

import com.example.finalproject.entitiy.BoardReelsCommentEntity;
import com.example.finalproject.util.UtilClass;
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
    private String timeAgo;

    public static BoardReelsCommentDTO toDTO(BoardReelsCommentEntity boardReelsCommentEntity) {
        BoardReelsCommentDTO boardReelsCommentDTO = new BoardReelsCommentDTO();
        boardReelsCommentDTO.setId(boardReelsCommentEntity.getId());
        boardReelsCommentDTO.setReelsId(boardReelsCommentEntity.getBoardReelsEntity().getId());
        boardReelsCommentDTO.setMemberId(boardReelsCommentEntity.getMemberEntity().getId());
        boardReelsCommentDTO.setCommentContents(boardReelsCommentEntity.getCommentContents());
        boardReelsCommentDTO.setCreatedAt(UtilClass.dateFormat(boardReelsCommentEntity.getCreatedAt()));
        return boardReelsCommentDTO;
    }

    public static BoardReelsCommentDTO toDTO2(BoardReelsCommentEntity boardReelsCommentEntity, String time) {
        BoardReelsCommentDTO boardReelsCommentDTO = new BoardReelsCommentDTO();
        boardReelsCommentDTO.setId(boardReelsCommentEntity.getId());
        boardReelsCommentDTO.setReelsId(boardReelsCommentEntity.getBoardReelsEntity().getId());
        boardReelsCommentDTO.setMemberId(boardReelsCommentEntity.getMemberEntity().getId());
        boardReelsCommentDTO.setCommentContents(boardReelsCommentEntity.getCommentContents());
        boardReelsCommentDTO.setCreatedAt(UtilClass.dateFormat(boardReelsCommentEntity.getCreatedAt()));
        boardReelsCommentDTO.setTimeAgo(time);
        return boardReelsCommentDTO;
    }
}
