package com.example.finalproject.entitiy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "memberFollow_table")
@Getter
@Setter
public class MemberFollowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
//    팔로우를 하는 사람의 번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private MemberEntity followerMemberEntity;

//    팔로우를 받아 팔로잉이 늘어난 사람의 번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private MemberEntity followingMemberEntity;
}
