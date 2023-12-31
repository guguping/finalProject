package com.example.finalproject.serivce;

import com.example.finalproject.domain.Role;
import com.example.finalproject.dto.BoardFileDTO;
import com.example.finalproject.dto.MemberDTO;
import com.example.finalproject.entitiy.MemberEntity;
import com.example.finalproject.entitiy.MemberFollowEntity;
import com.example.finalproject.entitiy.MemberSearchEntity;
import com.example.finalproject.repository.MemberRepository;
import com.example.finalproject.repository.SearchRepository;
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
import java.util.*;

@Service
@RequiredArgsConstructor
public class MemberSerivce {

    private final MemberRepository memberRepository;
    private final SearchRepository searchRepository;

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

//    public MemberDTO findByMemberEmailAndMemberPassword(MemberDTO memberDTO) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        System.out.println("암호화: " + encoder.encode(memberDTO.getMemberPassword()));
//        Optional<MemberEntity> memberEntityOptional = memberRepository.findByMemberEmailAndMemberPassword(memberDTO.getMemberEmail(), encoder.encode(memberDTO.getMemberPassword()));
//        System.out.println(memberEntityOptional.isPresent());
//        if (memberEntityOptional.isPresent()) {
//            return MemberDTO.toDTO(memberEntityOptional.get());
//        } else {
//            return null;
//        }
//    }

    public MemberDTO findByMemberEmailAndMemberPassword(MemberDTO memberDTO) {
        String rawPassword = memberDTO.getMemberPassword();

        Optional<MemberEntity> memberEntityOptional = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());

        if (memberEntityOptional.isPresent()) {
            MemberEntity memberEntity = memberEntityOptional.get();
            String hashedPassword = memberEntity.getMemberPassword();

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            boolean isPasswordMatch = encoder.matches(rawPassword, hashedPassword);

            if (isPasswordMatch) {
                return MemberDTO.toDTO(memberEntity);
            } else {
                return null; // 비밀번호가 일치하지 않는 경우 로그인 실패
            }
        } else {
            return null; // 해당 이메일로 등록된 회원이 없는 경우 로그인 실패
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

    @Transactional
    public void changePassword(MemberDTO memberDTO, String memberPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        memberDTO.setMemberPassword(encoder.encode(memberPassword));
        memberRepository.save(MemberEntity.toUpdateEntity(memberDTO));
    }

    public boolean checkPassword(String plainPassword, String encryptedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(plainPassword, encryptedPassword);
    }
  
    public void delete(Long id) {
        memberRepository.deleteById(id);

    }

    public void memberUpdate(MemberDTO upDTO) {
        memberRepository.save(MemberEntity.toUpdateEntity(upDTO));
    }

    public List<MemberDTO> searchMember(String q) {
        List<MemberEntity> memberEntityList = memberRepository.findByMemberNameContainingOrMemberNickNameContaining(q, q);
        List<MemberDTO> memberDTOList = new ArrayList<>();
        memberEntityList.forEach(memberEntity -> {
            memberDTOList.add(MemberDTO.toDTO(memberEntity));
        });
        return memberDTOList;
    }

    public void searchSaved(Long loginId, Long searchId) {
        MemberEntity loginEntity = memberRepository.findById(loginId).orElseThrow(() -> new NoSuchElementException());
        MemberEntity searchEntity = memberRepository.findById(searchId).orElseThrow(() -> new NoSuchElementException());
        MemberSearchEntity searchMemberEntity = MemberSearchEntity.toSaveEntity(loginEntity, searchEntity);
        searchRepository.save(searchMemberEntity);
    }

    public List<MemberDTO> searchFindAll(Long memberId) {
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(NoSuchElementException::new);
        List<MemberSearchEntity> memberSearchEntityList = searchRepository.findAllBySearchMember(memberEntity);

        List<MemberDTO> memberDTOList = new ArrayList<>();
        Set<Long> processedMemberIds = new HashSet<>();

        for (MemberSearchEntity memberSearchEntity : memberSearchEntityList) {
            Long memberSearchId = memberSearchEntity.getMemberSearch().getId();
            if (!processedMemberIds.contains(memberSearchId)) {
                Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(memberSearchId);
                if (optionalMemberEntity.isPresent()) {
                    MemberEntity foundMemberEntity = optionalMemberEntity.get();
                    memberDTOList.add(MemberDTO.toDTO(foundMemberEntity));
                    processedMemberIds.add(memberSearchId);
                }
            }
        }
        return memberDTOList;
    }

    @Transactional
    public void searchDelete(Long searchId, Long memberId) {
        System.out.println("searchId = " + searchId);
        MemberEntity searchUserEntity = memberRepository.findById(searchId).orElseThrow(() -> new NoSuchElementException());
        MemberEntity MemberEntity = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException());
        searchRepository.deleteBySearchUserEntityAndMemberEntity(searchUserEntity, MemberEntity);
    }

    public void searchDeleteAll(Long memberId) {
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException());
        searchRepository.deleteBySearchMember(memberEntity);
    }
}
