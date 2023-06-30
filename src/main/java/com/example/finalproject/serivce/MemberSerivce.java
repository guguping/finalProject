package com.example.finalproject.serivce;

import com.example.finalproject.dto.MemberDTO;
import com.example.finalproject.entitiy.MemberEntity;
import com.example.finalproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberSerivce {

    private final MemberRepository memberRepository;

    public Long save(MemberDTO memberDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        memberDTO.setMemberPassword(encoder.encode(memberDTO.getMemberPassword()));
        System.out.println(memberDTO.getMemberPassword());
        MemberEntity memberEntity = MemberEntity.toEntity(memberDTO);
        return memberRepository.save(memberEntity).getId();
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

    public MemberDTO findById(Long memberId) {
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException());// -> 익명함수 처리 (함수의 이름이 없는 함수)
        return MemberDTO.toDTO(memberEntity);
//        Optional<MemberEntity> memberEntityOptional = memberRepository.findById(id);
//        if (memberEntityOptional.isPresent()) {
//            // optional 객체에서 꺼내기
//            MemberEntity memberEntity = memberEntityOptional.get();
//            // BookEntity -> BookDTO 변환
//            MemberDTO memberDTO = MemberDTO.toDTO(memberEntity);
//            return memberDTO;
//        } else {
//            return null;
//        }

    }

}
