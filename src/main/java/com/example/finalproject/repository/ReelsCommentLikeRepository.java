package com.example.finalproject.repository;

import com.example.finalproject.entitiy.BoardReelsCommentEntity;
import com.example.finalproject.entitiy.BoardReelsCommentLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReelsCommentLikeRepository extends JpaRepository<BoardReelsCommentLikeEntity,Long> {
    Optional<BoardReelsCommentLikeEntity> findByBoardReelsCommentEntity(BoardReelsCommentEntity boardReelsCommentEntity);
}
