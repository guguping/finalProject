package com.example.finalproject.dto;

import com.example.finalproject.entitiy.BoardCommentEntity;
import com.example.finalproject.util.UtilClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardCommentDTO {
    private Long id;
    private Long boardId;
    private Long memberId;
    private String commentContents;
    private String createdAt;
    private String memberNickName;
    private String memberProfile;
    private String timeAgo;


//     0 = 비수정 / 1 = 수정
    private int commentUpdate = 0;

    public static BoardCommentDTO toDTO(BoardCommentEntity boardCommentEntity) {
        BoardCommentDTO boardCommentDTO = new BoardCommentDTO();
        boardCommentDTO.setId(boardCommentEntity.getId());
        boardCommentDTO.setCommentContents(boardCommentEntity.getCommentContents());
        boardCommentDTO.setBoardId(boardCommentEntity.getBoardEntity().getId());
        boardCommentDTO.setMemberId(boardCommentEntity.getMemberEntity().getId());
        boardCommentDTO.setCreatedAt(UtilClass.dateFormat(boardCommentEntity.getCreatedAt()));
        return boardCommentDTO;
    }

    public static BoardCommentDTO toDTO2(BoardCommentEntity boardCommentEntity, String time) {
        BoardCommentDTO boardCommentDTO = new BoardCommentDTO();
        boardCommentDTO.setId(boardCommentEntity.getId());
        boardCommentDTO.setCommentContents(boardCommentEntity.getCommentContents());
        boardCommentDTO.setBoardId(boardCommentEntity.getBoardEntity().getId());
        boardCommentDTO.setMemberId(boardCommentEntity.getMemberEntity().getId());
        boardCommentDTO.setCreatedAt(UtilClass.dateFormat(boardCommentEntity.getCreatedAt()));
        boardCommentDTO.setTimeAgo(time);
        return boardCommentDTO;
    }
}
