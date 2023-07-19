package com.example.finalproject.repository;

import com.example.finalproject.entitiy.BoardReelsEntity;
import com.example.finalproject.entitiy.BoardReelsLikeEntity;
import com.example.finalproject.entitiy.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardReelsLikeRepository extends JpaRepository<BoardReelsLikeEntity , Long> {
    List<BoardReelsLikeEntity> findAllByMemberEntityOrderByBoardReelsEntityIdAsc(MemberEntity memberEntity);

    List<BoardReelsLikeEntity> findByBoardReelsEntityOrderByIdDesc(BoardReelsEntity boardReelsEntity);
}
