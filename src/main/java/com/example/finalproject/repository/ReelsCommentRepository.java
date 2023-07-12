package com.example.finalproject.repository;

import com.example.finalproject.entitiy.BoardReelsCommentEntity;
import com.example.finalproject.entitiy.BoardReelsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReelsCommentRepository extends JpaRepository<BoardReelsCommentEntity,Long> {
    List<BoardReelsCommentEntity> findByBoardReelsEntityOrderByIdDesc(BoardReelsEntity boardReelsEntity);
}
