package com.example.finalproject.entitiy;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "board_reels_bookmark_table")
@Getter
@Setter
public class BoardReelsBookmarkEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reels_id")
    private BoardReelsEntity boardReelsEntity;

    public static BoardReelsBookmarkEntity toSavedEntity(BoardReelsEntity boardReelsEntity, MemberEntity memberEntity) {
        BoardReelsBookmarkEntity boardReelsBookmarkEntity = new BoardReelsBookmarkEntity();
        boardReelsBookmarkEntity.setBoardReelsEntity(boardReelsEntity);
        boardReelsBookmarkEntity.setMemberEntity(memberEntity);
        return boardReelsBookmarkEntity;
    }
}
