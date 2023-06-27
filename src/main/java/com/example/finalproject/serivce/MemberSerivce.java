package com.example.finalproject.serivce;

import com.example.finalproject.dto.MemberDTO;
import com.example.finalproject.entitiy.MemberEntity;
import com.example.finalproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberSerivce {
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {

    }

    public MemberDTO findByMemberEmailAndMemberPassword(MemberDTO memberDTO) {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findByMemberEmailAndMemberPassword(memberDTO.getMemberEmail(), memberDTO.getMemberPassword());
        System.out.println(memberEntityOptional.isPresent());
        if (memberEntityOptional.isPresent()) {
            return MemberDTO.toDTO(memberEntityOptional.get());
        } else {
            return null;
        }
    }
}
