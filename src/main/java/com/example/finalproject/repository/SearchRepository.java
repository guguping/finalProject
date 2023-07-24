package com.example.finalproject.repository;

import com.example.finalproject.entitiy.MemberSearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<MemberSearchEntity,Long> {
}
