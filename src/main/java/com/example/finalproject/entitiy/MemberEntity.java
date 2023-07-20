package com.example.finalproject.entitiy;

import com.example.finalproject.domain.Role;
import com.example.finalproject.dto.MemberDTO;
import com.example.finalproject.kakaoDTO.KakaoProfile;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "member_table")
@Getter
@Setter
public class MemberEntity extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30,unique = true)
    private String memberEmail;

    @Column(length = 500,nullable = false)
    private String memberPassword;

    @Column(length = 20,nullable = false)
    private String memberName;

    @Column(length = 10,nullable = false)
    private String memberBirth;

    @Column(length = 13,unique = true)
    private String memberMobile;

    @Column(length = 10,nullable = false)
    private String memberNickName;

    @Column(length = 20)
    private String memberGender;

//    회원 소개
    @Column(length = 150)
    private String memberText;

//    멤버 프로필 사진 스토리지 네임
    @Column(length = 200)
    private String memberProfile;

//    릴스 업로드 유무
    @Column()
    private int reelsAttached;

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    private String oauth;


    public static MemberEntity toEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberBirth(memberDTO.getMemberBirth());
        memberEntity.setMemberMobile(memberDTO.getMemberMobile());
        memberEntity.setMemberNickName(memberDTO.getMemberNickName());
        memberEntity.setMemberGender(memberDTO.getMemberGender());
        memberEntity.setMemberText(memberDTO.getMemberText());
        memberEntity.setMemberProfile(memberDTO.getMemberProfile());
        memberEntity.setReelsAttached(memberDTO.getReelsAttached());
        memberEntity.setRole(Role.ROLE_MEMBER);
        return memberEntity;
    }

    public static MemberEntity createKakaoMember(KakaoProfile kakaoProfile, String cosKey) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(kakaoProfile.getKakao_account().getEmail());
        memberEntity.setMemberName(kakaoProfile.getProperties().getNickname());
        memberEntity.setMemberNickName(generateMemberNickname());
        memberEntity.setMemberBirth("1999" + kakaoProfile.getProperties().getBirthday());
        memberEntity.setMemberGender(kakaoProfile.getProperties().getGender());
        memberEntity.setOauth("kakao");
        // 나머지 필요한 속성 설정

        return memberEntity;
    }

    private static int memberCount = 0;

    // 회원 닉네임 생성 메서드
    private static String generateMemberNickname() {
        memberCount++; // 회원 수 증가
        return "kakaoUser" + memberCount;
    }

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

    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<BoardCommentEntity> boardCommentEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardBookmarkEntity> boardBookmarkEntityList = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(Role.ROLE_MEMBER.name()));
    }

    @Override
    public String getPassword() {
        return memberPassword;
    }


    @Override
    public String getUsername() {
        return memberEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
