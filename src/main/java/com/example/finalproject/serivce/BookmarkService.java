package com.example.finalproject.serivce;

import com.example.finalproject.entitiy.BoardBookmarkEntity;
import com.example.finalproject.entitiy.BoardEntity;
import com.example.finalproject.entitiy.BoardLikeEntity;
import com.example.finalproject.entitiy.MemberEntity;
import com.example.finalproject.repository.BoardBookmarkRepository;
import com.example.finalproject.repository.BoardRepository;
import com.example.finalproject.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookmarkService {
    private final BoardBookmarkRepository boardBookmarkRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public boolean findByBoardBookmark(Long boardId, Long memberId, int boardKind) {
        BoardEntity boardEntity = boardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException());
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException());
        Optional<BoardBookmarkEntity> optionalBoardBookmarkEntity = Optional.of(new BoardBookmarkEntity());

        if (boardKind == 1) {
            optionalBoardBookmarkEntity = boardBookmarkRepository.findByBoardEntityAndMemberEntity(boardEntity, memberEntity);
        }
        if (optionalBoardBookmarkEntity.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    public Long save(Long boardId, Long memberId, int boardKind) {
        BoardEntity boardEntity = boardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException());
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException());
        BoardBookmarkEntity boardBookmarkEntity = BoardBookmarkEntity.toSaveEntity(boardEntity, memberEntity);
        Long bookmarkOk = null;

        if (boardKind == 1) {
            bookmarkOk = boardBookmarkRepository.save(boardBookmarkEntity).getId();
        }
        return bookmarkOk;
    }

    @Transactional
    public void delete(Long boardId, Long memberId, int boardKind) {
        BoardEntity boardEntity = boardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException());
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException());

        if (boardKind == 1) {
            boardBookmarkRepository.deleteByBoardEntityAndMemberEntity(boardEntity, memberEntity);
        }
    }
}
