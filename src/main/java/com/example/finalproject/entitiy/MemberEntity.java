package com.example.finalproject.entitiy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member_table")
@Getter
@Setter
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 30,unique = true)
    String memberEmail;

    @Column(length = 30,nullable = false)
    String memberPassword;

    @Column(length = 20,nullable = false)
    String memberName;

    @Column(length = 10,nullable = false)
    String memberBirth;

    @Column(length = 13,unique = true)
    String memberMobile;

    @Column(length = 10,nullable = false)
    String memberNickName;

    @Column(length = 20)
    String memberGender;

//    회원 소개
    @Column(length = 150)
    String memberText;

//    멤버 프로필 사진 스토리지 네임
    @Column(length = 200)
    String memberProfile;

//    릴스 업로드 유무
    @Column()
    int reelsAttached;

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardStoryEntity> boardStoryEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "followerMemberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MemberFollowEntity> followerMemberEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "followingMemberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MemberFollowEntity> followingMemberEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardAnswerEntity> boardAnswerEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardLikeEntity> boardLikeEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardCommentLikeEntity> boardCommentLikeEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardStoryLikeEntity> boardStoryLikeEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardReelsEntity> boardReelsEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardReelsLikeEntity> boardReelsLikeEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<BoardReelsCommentEntity> boardReelsCommentEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<BoardReelsAnswerEntity> boardReelsAnswerEntityList = new ArrayList<>();
}
