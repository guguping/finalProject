package com.example.finalproject.repository;

import com.example.finalproject.entitiy.BoardFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Long> {
}
