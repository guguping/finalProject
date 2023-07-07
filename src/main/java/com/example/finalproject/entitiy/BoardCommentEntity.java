package com.example.finalproject.entitiy;

import com.example.finalproject.dto.BoardCommentDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "boardComment_table")
@Getter
@Setter
public class BoardCommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100,nullable = false)
    private String commentContents;
    
//    댓글 수정 여부
    @Column
    private int commentUpdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "boardCommentEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardAnswerEntity> boardAnswerEntityArrayList = new ArrayList<>();

    @OneToMany(mappedBy = "boardCommentEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardCommentLikeEntity> boardCommentLikeEntityList = new ArrayList<>();

    public static BoardCommentEntity toSaveEntity(BoardEntity boardEntity,MemberEntity memberEntity, BoardCommentDTO boardCommentDTO) {
        BoardCommentEntity boardCommentEntity = new BoardCommentEntity();
        boardCommentEntity.setCommentContents(boardCommentDTO.getCommentContents());
        boardCommentEntity.setMemberEntity(memberEntity);
        boardCommentEntity.setBoardEntity(boardEntity);
        return boardCommentEntity;
    }

}
