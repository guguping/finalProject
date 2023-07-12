package com.example.finalproject.serivce;

import com.example.finalproject.dto.BoardDTO;
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
        List<MemberFollowEntity> memberFollowerEntityList = memberFollowRepository.findByFollowerMemberEntity(memberEntity);
        List<MemberFollowDTO> memberFollowerDTOList = new ArrayList<>();
        memberFollowerEntityList.forEach(memberFollowEntity -> {
            memberFollowerDTOList.add(MemberFollowDTO.toDTO(memberFollowEntity));
        });
        return memberFollowerDTOList;
    }

    public List<MemberFollowDTO> findByFollowing(Long memberId) {
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException());
        List<MemberFollowEntity> memberFollowingEntityList = memberFollowRepository.findByFollowingMemberEntity(memberEntity);
        List<MemberFollowDTO> memberFollowingDTOList = new ArrayList<>();
        memberFollowingEntityList.forEach(memberFollowEntity -> {
            memberFollowingDTOList.add(MemberFollowDTO.toDTO(memberFollowEntity));
        });
        return memberFollowingDTOList;
    }
}
