package com.example.finalproject.repository;

import com.example.finalproject.entitiy.BoardReelsBookmarkEntity;
import com.example.finalproject.entitiy.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardReelsBookMarkRepository extends JpaRepository<BoardReelsBookmarkEntity, Long> {
    List<BoardReelsBookmarkEntity> findAllByMemberEntityOrderByBoardReelsEntityIdAsc(MemberEntity memberEntity);
}
