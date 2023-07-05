package com.example.finalproject.serivce;

import com.example.finalproject.dto.BoardDTO;
import com.example.finalproject.dto.MemberFollowDTO;
import com.example.finalproject.entitiy.BoardEntity;
import com.example.finalproject.entitiy.MemberEntity;
import com.example.finalproject.entitiy.MemberFollowEntity;
import com.example.finalproject.repository.MemberFollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberFollowService {
    private final MemberFollowRepository memberFollowRepository;
    private final BoardService boardService;

    public  List<MemberFollowDTO> findAll() {
        List<MemberFollowEntity> memberFollowEntityList = memberFollowRepository.findAll();
        List<MemberFollowDTO> memberFollowDTOList = new ArrayList<>();
        memberFollowEntityList.forEach(memberFollowEntity -> {
            memberFollowDTOList.add(MemberFollowDTO.toDTO(memberFollowEntity));
        });
        return memberFollowDTOList;
    }
}
