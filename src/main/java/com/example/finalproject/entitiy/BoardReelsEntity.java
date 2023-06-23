package com.example.finalproject.entitiy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board_reels_table")
@Getter
@Setter
public class BoardReelsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 200,nullable = false)
    String reelsFile;

    @Column(length = 100)
    String reelsContents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "boardReelsEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardReelsLikeEntity> boardReelsLikeEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "boardReelsEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<BoardReelsCommentEntity> boardReelsCommentEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "boardReelsEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<BoardReelsBookmarkEntity> boardReelsBookmarkEntityList = new ArrayList<>();
}
