package com.example.finalproject.repository;


import com.example.finalproject.entitiy.MemberEntity;
import com.example.finalproject.entitiy.MemberFollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberFollowRepository extends JpaRepository<MemberFollowEntity,Long> {

//    List<MemberFollowEntity> findByFollowerId(Long memberId);
}
