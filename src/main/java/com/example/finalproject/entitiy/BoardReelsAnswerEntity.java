package com.example.finalproject.entitiy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "board_reelsAnswer_table")
@Getter
@Setter
public class BoardReelsAnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 255,nullable = false)
    String answerContents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reelsComment_id")
    private BoardReelsCommentEntity boardReelsCommentEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;
}
