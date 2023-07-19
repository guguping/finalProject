package com.example.finalproject.dto;

import com.example.finalproject.entitiy.MemberFollowEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberFollowDTO {
    private Long id;

//    팔로우를 하는 사람의 번호
    private Long followerId;

//    팔로우를 받아 팔로잉이 늘어난 사람의 번호
    private Long followingId;

    private String memberNickName;
    private String memberName;
    private String memberProfile;

    public static MemberFollowDTO toDTO(MemberFollowEntity memberFollowEntity){
        MemberFollowDTO memberFollowDTO = new MemberFollowDTO();
        memberFollowDTO.setId(memberFollowEntity.getId());
        memberFollowDTO.setFollowerId(memberFollowEntity.getFollowerMemberEntity().getId());
        memberFollowDTO.setFollowingId(memberFollowEntity.getFollowingMemberEntity().getId());

        return memberFollowDTO;
    }
}
