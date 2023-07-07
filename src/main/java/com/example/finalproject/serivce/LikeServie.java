package com.example.finalproject.serivce;

import com.example.finalproject.dto.LikeDTO;
import com.example.finalproject.entitiy.BoardEntity;
import com.example.finalproject.entitiy.BoardLikeEntity;
import com.example.finalproject.entitiy.MemberEntity;
import com.example.finalproject.repository.BoardLikeRepository;
import com.example.finalproject.repository.BoardRepository;
import com.example.finalproject.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LikeServie {
    private final BoardLikeRepository boardLikeRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    public boolean findByBoardLike(Long boardId, Long memberId, int boardKind) {
        BoardEntity boardEntity = boardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException());
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException());
        Optional<BoardLikeEntity> optionalBoardLikeEntity = Optional.of(new BoardLikeEntity());

        if (boardKind == 1) {
            optionalBoardLikeEntity = boardLikeRepository.findByBoardEntityAndMemberEntity(boardEntity, memberEntity);
        }
        if (optionalBoardLikeEntity.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    public Long save(Long boardId, Long memberId, int boardKind) {
        BoardEntity boardEntity = boardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException());
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException());
        BoardLikeEntity boardLikeEntity = BoardLikeEntity.toSaveEntity(boardEntity, memberEntity);
        Long likeOk = null;

        if (boardKind == 1) {
            likeOk = boardLikeRepository.save(boardLikeEntity).getId();
        }
        return likeOk;
    }

    @Transactional
    public void delete(Long boardId, Long memberId, int boardKind) {
        BoardEntity boardEntity = boardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException());
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException());

        if (boardKind == 1) {
            boardLikeRepository.deleteByBoardEntityAndMemberEntity(boardEntity, memberEntity);
        }
    }
}
