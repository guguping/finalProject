package com.example.finalproject.entitiy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board_reels_comment_table")
@Getter
@Setter
public class BoardReelsCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 255,nullable = false)
    String commentContents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reels_id")
    private BoardReelsEntity boardReelsEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "boardReelsCommentEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardReelsAnswerEntity> boardReelsAnswerEntityList = new ArrayList<>();
}