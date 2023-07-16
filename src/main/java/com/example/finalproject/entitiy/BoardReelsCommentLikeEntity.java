package com.example.finalproject.entitiy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "board_reels_comment_like_table")
@Getter
@Setter
public class BoardReelsCommentLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private  BoardReelsCommentEntity boardReelsCommentEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    public static BoardReelsCommentLikeEntity toSaveEntity(BoardReelsCommentEntity boardReelsCommentEntity, MemberEntity memberEntity) {
        BoardReelsCommentLikeEntity boardReelsCommentLikeEntity = new BoardReelsCommentLikeEntity();
        boardReelsCommentLikeEntity.setMemberEntity(memberEntity);
        boardReelsCommentLikeEntity.setBoardReelsCommentEntity(boardReelsCommentEntity);
        return boardReelsCommentLikeEntity;
    }
}
