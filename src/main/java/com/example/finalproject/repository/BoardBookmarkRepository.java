package com.example.finalproject.repository;

import com.example.finalproject.entitiy.BoardBookmarkEntity;
import com.example.finalproject.entitiy.BoardEntity;
import com.example.finalproject.entitiy.BoardLikeEntity;
import com.example.finalproject.entitiy.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardBookmarkRepository extends JpaRepository<BoardBookmarkEntity, Long> {
    Optional<BoardBookmarkEntity> findByBoardEntityAndMemberEntity(BoardEntity boardEntity, MemberEntity memberEntity);

    void deleteByBoardEntityAndMemberEntity(BoardEntity boardEntity, MemberEntity memberEntity);

}
