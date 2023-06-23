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
public class BoardReelsEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200,nullable = false)
    private String reelsFile;

    @Column(length = 100)
    private String reelsContents;

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
