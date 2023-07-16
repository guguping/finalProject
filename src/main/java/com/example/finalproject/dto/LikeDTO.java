package com.example.finalproject.dto;

import com.example.finalproject.entitiy.BoardLikeEntity;
import com.example.finalproject.entitiy.BoardReelsCommentEntity;
import com.example.finalproject.entitiy.BoardReelsCommentLikeEntity;
import com.example.finalproject.repository.ReelsCommentLikeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeDTO {
    private Long id;
    private Long boardId;
    private Long commentId;
    private Long memberId;
    private int LikeKind;

    public static LikeDTO reelsCommentLikeToDTO(BoardReelsCommentLikeEntity boardReelsCommentLikeEntity) {
        LikeDTO likeDTO = new LikeDTO();
        if (boardReelsCommentLikeEntity == null){
            return null;
        } else {
            likeDTO.setId(boardReelsCommentLikeEntity.getId());
            likeDTO.setMemberId(boardReelsCommentLikeEntity.getMemberEntity().getId());
            likeDTO.setCommentId(boardReelsCommentLikeEntity.getBoardReelsCommentEntity().getId());
            return likeDTO;
        }
    }
}
