package com.example.finalproject.repository;

import com.example.finalproject.entitiy.BoardEntity;
import com.example.finalproject.entitiy.BoardFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Long> {
    List<BoardFileEntity> findByBoardEntity(BoardEntity boardEntity);
}
