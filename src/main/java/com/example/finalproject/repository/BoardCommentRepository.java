package com.example.finalproject.repository;

import com.example.finalproject.entitiy.BoardCommentEntity;

import com.example.finalproject.entitiy.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardCommentRepository extends JpaRepository<BoardCommentEntity, Long> {
    List<BoardCommentEntity> findByBoardEntityOrderByIdDesc(BoardEntity boardEntity);

    List<BoardCommentEntity> findByBoardEntity(BoardEntity boardEntity);
}
