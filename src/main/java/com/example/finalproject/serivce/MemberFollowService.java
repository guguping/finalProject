package com.example.finalproject.serivce;

import com.example.finalproject.dto.BoardDTO;
import com.example.finalproject.dto.MemberDTO;
import com.example.finalproject.dto.MemberFollowDTO;
import com.example.finalproject.entitiy.BoardEntity;
import com.example.finalproject.entitiy.MemberEntity;
import com.example.finalproject.entitiy.MemberFollowEntity;
import com.example.finalproject.repository.MemberFollowRepository;
import com.example.finalproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberFollowService {
    private final MemberFollowRepository memberFollowRepository;
    private final BoardService boardService;
    private final MemberRepository memberRepository;

    public  List<MemberFollowDTO> findAll() {
        List<MemberFollowEntity> memberFollowEntityList = memberFollowRepository.findAll();
        List<MemberFollowDTO> memberFollowDTOList = new ArrayList<>();
        memberFollowEntityList.forEach(memberFollowEntity -> {
            memberFollowDTOList.add(MemberFollowDTO.toDTO(memberFollowEntity));
        });
        return memberFollowDTOList;
    }

    public List<MemberFollowDTO> findByFollower(Long memberId) {
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException());
        List<MemberFollowEntity> memberFollowerEntityList = memberFollowRepository.findByFollowingMemberEntity(memberEntity);
        List<MemberFollowDTO> followerDTOList = new ArrayList<>();
        memberFollowerEntityList.forEach(memberFollowEntity -> {
            followerDTOList.add(MemberFollowDTO.toDTO(memberFollowEntity));
        });
        List<MemberFollowDTO> memberFollowerDTOList = new ArrayList<>();
        for (int i = 0; i < followerDTOList.size(); i++) {
            MemberFollowDTO memberFollowDTO = followerDTOList.get(i);
            MemberDTO memberDTO = MemberDTO.toDTO(memberRepository.findById(memberFollowDTO.getFollowerId()).orElseThrow(() -> new NoSuchElementException()));
            memberFollowDTO.setMemberNickName(memberDTO.getMemberNickName());
            memberFollowDTO.setMemberProfile(memberDTO.getMemberProfile());
            memberFollowDTO.setMemberName(memberDTO.getMemberName());
            memberFollowerDTOList.add(memberFollowDTO);
        }
        return memberFollowerDTOList;
    }

    public List<MemberFollowDTO> findByFollowing(Long memberId) {
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException());
        List<MemberFollowEntity> memberFollowingEntityList = memberFollowRepository.findByFollowerMemberEntity(memberEntity);
        List<MemberFollowDTO> followingDTOList = new ArrayList<>();
        memberFollowingEntityList.forEach(memberFollowEntity -> {
            followingDTOList.add(MemberFollowDTO.toDTO(memberFollowEntity));
        });
        List<MemberFollowDTO> memberFollowingDTOList = new ArrayList<>();
        for (int i = 0; i < followingDTOList.size(); i++) {
            MemberFollowDTO memberFollowDTO = followingDTOList.get(i);
            MemberDTO memberDTO = MemberDTO.toDTO(memberRepository.findById(memberFollowDTO.getFollowingId()).orElseThrow(() -> new NoSuchElementException()));
            memberFollowDTO.setMemberNickName(memberDTO.getMemberNickName());
            memberFollowDTO.setMemberProfile(memberDTO.getMemberProfile());
            memberFollowDTO.setMemberName(memberDTO.getMemberName());
            memberFollowingDTOList.add(memberFollowDTO);
        }
        return memberFollowingDTOList;
    }

    public void followDelete(Long id) {
        memberFollowRepository.deleteById(id);
    }

    public void save(Long id, Long loginId) {
        MemberEntity memberEntity = memberRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        MemberEntity loginMemberEntity = memberRepository.findById(loginId).orElseThrow(() -> new NoSuchElementException());
        MemberFollowEntity memberFollowEntity = MemberFollowEntity.toSaveEntity(memberEntity, loginMemberEntity);
        memberFollowRepository.save(memberFollowEntity);
    }

    public void deleteByFollow(Long id, Long loginId) {
        MemberEntity memberEntity = memberRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        MemberEntity loginMemberEntity = memberRepository.findById(loginId).orElseThrow(() -> new NoSuchElementException());
        memberFollowRepository.deleteByFollowingMemberEntityAndFollowerMemberEntity(memberEntity, loginMemberEntity);
    }
}
