package com.example.finalproject.serivce;

import com.example.finalproject.domain.Role;
import com.example.finalproject.dto.BoardFileDTO;
import com.example.finalproject.dto.MemberDTO;
import com.example.finalproject.entitiy.MemberEntity;
import com.example.finalproject.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        memberDTO.setRole(Role.ROLE_MEMBER);
        String defaultImageFileName = "defaultImage.jpg";
        memberDTO.setMemberProfile(defaultImageFileName);
        MemberEntity memberEntity = MemberEntity.toEntity(memberDTO);
        return memberRepository.save(memberEntity).getId();
    }

//    @Override
//    public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
//        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(memberEmail);
//        MemberEntity memberEntity = optionalMemberEntity.get();
//
//    }

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
        System.out.println("memberId = " + memberId);
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

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        memberEntityList.forEach(memberEntity -> {
            memberDTOList.add(MemberDTO.toDTO(memberEntity));
        });
        return memberDTOList;
    }


    public boolean mailCheck(String memberEmail) {
        return memberRepository.existsByMemberEmail(memberEmail);
    }

    public boolean nicknameCheck(String memberNickname) {
        return memberRepository.existsByMemberNickName(memberNickname);
    }

    @Transactional
    public void profileUpdate(MemberDTO memberDTO) throws IOException {
        // 현재 profile 사진 파일 삭제
        MemberEntity originalMemberEntity = memberRepository.findById(memberDTO.getId()).orElseThrow(() -> new NoSuchElementException());
        MemberDTO originalMemberDTO = MemberDTO.toDTO(originalMemberEntity);
        // 기본 이미지일 경우 삭제되지 않음
        if (!originalMemberDTO.getMemberProfile().equals("defaultImage.jpg")) {
            String deletePath = "C:\\project_img\\" + originalMemberDTO.getMemberProfile();
            File file = new File(deletePath);
            boolean deleted = file.delete();
            if (deleted) {
                System.out.println("파일 삭제 성공");
            } else {
                System.out.println("파일 삭제 실패");
            }
        }

        // profile 사진 변경
        String originalFileName = memberDTO.getMemberFile().getOriginalFilename();
        String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
        String savePath = "C:\\project_img\\" + storedFileName;
        memberDTO.getMemberFile().transferTo(new File(savePath));
        memberDTO.setMemberProfile(storedFileName);

        memberRepository.updateProfile(memberDTO.getId(), memberDTO.getMemberProfile());
    }

    @Transactional
    public void profileDelete(Long loginId) {
        MemberEntity originalMemberEntity = memberRepository.findById(loginId).orElseThrow(() -> new NoSuchElementException());
        MemberDTO originalMemberDTO = MemberDTO.toDTO(originalMemberEntity);
        // 기본 이미지일 경우 삭제되지 않음
        if (!originalMemberDTO.getMemberProfile().equals("defaultImage.jpg")) {
            String deletePath = "C:\\project_img\\" + originalMemberDTO.getMemberProfile();
            File file = new File(deletePath);
            boolean deleted = file.delete();
            if (deleted) {
                System.out.println("파일 삭제 성공");
            } else {
                System.out.println("파일 삭제 실패");
            }
        }

        // 기본 이미지로 변경
        String originalFileName = "defaultImage.jpg";
        memberRepository.updateProfile(loginId, originalFileName);
    }

    public Long findByMemberEmail(String memberEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(memberEmail);
        if (optionalMemberEntity.isPresent()) {
            MemberEntity memberEntity = optionalMemberEntity.get();
            return memberEntity.getId();
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }
}
