package com.example.finalproject.dto;

import com.example.finalproject.domain.Role;
import com.example.finalproject.entitiy.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberBirthM;
    private String memberBirthD;
    private String memberBirthY;
    private String memberBirth;
    private String memberMobile;
    private String memberNickName;
    private String memberGender;
    private String memberText;
    private int reelsAttached = 0;
    private String memberProfile;

    private Role role;

    public static MemberDTO toDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberBirth(memberEntity.getMemberBirth());
        memberDTO.setMemberMobile(memberEntity.getMemberMobile());
        memberDTO.setMemberNickName(memberEntity.getMemberNickName());
        memberDTO.setMemberNickName(memberEntity.getMemberNickName());
        memberDTO.setMemberGender(memberEntity.getMemberGender());
        memberDTO.setMemberText(memberEntity.getMemberText());
        memberDTO.setReelsAttached(memberEntity.getReelsAttached());
        memberDTO.setMemberProfile(memberEntity.getMemberProfile());
        memberDTO.setRole(Role.ROLE_MEMBER);
        return memberDTO;
    }

}
