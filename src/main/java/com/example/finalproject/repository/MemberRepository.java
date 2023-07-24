package com.example.finalproject.repository;


import com.example.finalproject.entitiy.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
    Optional<MemberEntity> findByMemberEmailAndMemberPassword(String memberEmail, String memberPassword);

    Optional<MemberEntity> findByMemberEmail(String memberEmail);

     boolean existsByMemberEmail(String memberEmail);

     boolean existsByMemberNickName(String memberNickName);

    @Modifying
    @Query(value = "update member_table set member_profile=:memberProfile where id=:id", nativeQuery = true)
    void updateProfile(@Param("id") Long id, @Param("memberProfile") String memberProfile);

    List<MemberEntity> findByMemberNameContainingOrMemberNickNameContaining(String q, String q1);
}
