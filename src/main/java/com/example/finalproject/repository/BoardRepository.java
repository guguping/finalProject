package com.example.finalproject.repository;

import com.example.finalproject.entitiy.BoardEntity;
import com.example.finalproject.entitiy.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
    List<BoardEntity> findByMemberEntityOrderByIdDesc(MemberEntity memberEntity);
    List<BoardEntity> findAllByOrderByIdDesc();

}
