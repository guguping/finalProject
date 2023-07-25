package com.example.finalproject.entitiy;

import com.example.finalproject.dto.BoardDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board_table")
@Getter
@Setter
public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String boardContents;
    
//    게시글 공개 여부 디폴트 1 = 공개
    @Column
    private int boardBlind;

//    게시글 수정 여부 디폴트 0 = 비수정
    @Column
    private int boardUpdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardFileEntity> boardFileEntityList = new ArrayList<>();

//
    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardCommentEntity> boardCommentEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardLikeEntity> boardLikeEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "boardEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<BoardBookmarkEntity> boardBookmarkEntityList = new ArrayList<>();

    public static BoardEntity toSaveEntity(BoardDTO boardDTO, MemberEntity memberEntity) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setMemberEntity(memberEntity);
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardBlind(1);
        boardEntity.setBoardUpdate(0);
        return boardEntity;
    }

    public static BoardEntity toUpdateEntity(BoardDTO boardDTO, MemberEntity memberEntity) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDTO.getId());
        boardEntity.setMemberEntity(memberEntity);
        boardEntity.setBoardContents(boardDTO.getBoardContents());

        return boardEntity;
    }
}
