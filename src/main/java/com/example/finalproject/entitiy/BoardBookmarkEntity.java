package com.example.finalproject.entitiy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "board_bookmark_table")
@Getter
@Setter
public class BoardBookmarkEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    public static BoardBookmarkEntity toSaveEntity(BoardEntity boardEntity, MemberEntity memberEntity) {
        BoardBookmarkEntity boardBookmarkEntity = new BoardBookmarkEntity();
        boardBookmarkEntity.setBoardEntity(boardEntity);
        boardBookmarkEntity.setMemberEntity(memberEntity);
        return boardBookmarkEntity;
    }
}
