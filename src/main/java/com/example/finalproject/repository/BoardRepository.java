package com.example.finalproject.repository;

import com.example.finalproject.entitiy.BoardEntity;
import com.example.finalproject.entitiy.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {


    @Query(value = "select * from board_table b left join member_follow_table f on b.member_id = f.following_id where f.follower_id = :id or b.member_id = :id order by b.id desc", nativeQuery = true)
    List<BoardEntity> findAllByFollowerIdOrMemberId(@Param("id") Long id);

    List<BoardEntity> findByMemberEntityOrderByIdDesc(MemberEntity memberEntity);
    List<BoardEntity> findAllByOrderByIdDesc();

//    @Modifying
//    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id")
//    void updateHits(@Param("id") Long id);

//    select b.*, f.* from board_table b left join member_follow_table f on b.member_id = f.following_id where f.follower_id = 1 or member_id = 1;
//    select b.*, f.* from board_table b left join member_follow_table f

}
