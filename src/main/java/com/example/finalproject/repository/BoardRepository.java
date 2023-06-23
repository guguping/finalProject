package com.example.finalproject.repository;

import com.example.finalproject.entitiy.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
}
