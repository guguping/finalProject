package com.example.finalproject.serivce;

import com.example.finalproject.dto.BoardDTO;
import com.example.finalproject.dto.MemberFollowDTO;
import com.example.finalproject.entitiy.MemberEntity;
import com.example.finalproject.entitiy.MemberFollowEntity;
import com.example.finalproject.repository.MemberFollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberFollowService {
    private final MemberFollowRepository memberFollowRepository;
    private final BoardService boardService;

    public  List<MemberFollowDTO> findFollow(Long followerId) {
        MemberEntity follower = new MemberEntity();
        follower.setId(followerId);

        List<MemberFollowEntity> follows = memberFollowRepository.findByFollowerMemberEntity(follower);
        List<MemberFollowDTO> memberFollowDTOList = follows.stream()
                .map(follow -> {
                    MemberFollowDTO memberFollowDTO = new MemberFollowDTO();
                    memberFollowDTO.setId(follow.getFollowingMemberEntity().getId());
                    memberFollowDTO.setFollowerId(follow.getFollowingMemberEntity().getId());
                    memberFollowDTO.setFollowingId(follow.getFollowingMemberEntity().getId());


                    return memberFollowDTO;
                })
                .collect(Collectors.toList());
        return memberFollowDTOList;
    }
}
