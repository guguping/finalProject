package com.example.finalproject.repository;

import com.example.finalproject.entitiy.MemberEntity;
import com.example.finalproject.entitiy.MemberSearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SearchRepository extends JpaRepository<MemberSearchEntity,Long> {
    @Query("SELECT mse FROM MemberSearchEntity mse WHERE mse.searchMember = :memberEntity")
    List<MemberSearchEntity> findAllBySearchMember(@Param("memberEntity") MemberEntity memberEntity);

    @Transactional
    @Modifying
    @Query("DELETE FROM MemberSearchEntity mse WHERE mse.searchMember = :memberEntity AND mse.memberSearch = :searchUserEntity")
    void deleteBySearchUserEntityAndMemberEntity(@Param("searchUserEntity") MemberEntity searchUserEntity, @Param("memberEntity") MemberEntity memberEntity);

    @Transactional
    @Modifying
    @Query("DELETE FROM MemberSearchEntity mse WHERE mse.searchMember = :memberEntity")
    void deleteBySearchMember(@Param("memberEntity") MemberEntity memberEntity);
}
