package com.example.finalproject.serivce;
import com.example.finalproject.dto.BoardCommentDTO;
import com.example.finalproject.entitiy.BoardCommentEntity;
import com.example.finalproject.entitiy.BoardEntity;
import com.example.finalproject.entitiy.MemberEntity;
import com.example.finalproject.repository.BoardCommentRepository;
import com.example.finalproject.repository.BoardRepository;
import com.example.finalproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BoardCommentService {
    private final BoardCommentRepository boardCommentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Long save(BoardCommentDTO commentDTO) {
        BoardEntity boardEntity = boardRepository.findById(commentDTO.getBoardId()).orElseThrow(() -> new NoSuchElementException());
        MemberEntity memberEntity = memberRepository.findById(commentDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        BoardCommentEntity boardCommentEntity = BoardCommentEntity.toSaveEntity(boardEntity,memberEntity, commentDTO );
        return boardCommentRepository.save(boardCommentEntity).getId();
    }

    @Transactional
    public List<BoardCommentDTO> findAll(Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException());
        // 1. BoardEntity에서 댓글 목록 가져오기
//        List<CommentEntity> commentEntityList = boardEntity.getCommentEntityList();
        // 2. CommentRepository에서 가져오기
        // select * from comment_table where board_id=?
        List<BoardCommentEntity> commentEntityList = boardCommentRepository.findByBoardEntityOrderByIdDesc(boardEntity);

        List<BoardCommentDTO> commentDTOList = new ArrayList<>();
        commentEntityList.forEach(comment -> {
            commentDTOList.add(BoardCommentDTO.toDTO(comment));
        });
        return commentDTOList;
    }
}








