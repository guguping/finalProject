package com.example.finalproject.repository;


import com.example.finalproject.entitiy.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
    Optional<MemberEntity> findByMemberEmailAndMemberPassword(String memberEmail, String memberPassword);

}
