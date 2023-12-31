package com.example.finalproject.serivce;

import com.example.finalproject.dto.BoardDTO;
import com.example.finalproject.dto.BoardFileDTO;
import com.example.finalproject.dto.MemberDTO;
import com.example.finalproject.dto.MemberFollowDTO;
import com.example.finalproject.entitiy.*;
import com.example.finalproject.repository.*;
import com.example.finalproject.util.UtilClass;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;
    private final MemberRepository memberRepository;
    private final BoardCommentRepository boardCommentRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final BoardBookmarkRepository boardBookmarkRepository;
    private final UtilClass utilClass;

    public Long save(BoardDTO boardDTO) throws IOException {
        // 로그인한 아이디의 memberDTO 가져오기
        MemberEntity memberEntity = memberRepository.findById(boardDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        // 1. Board 테이블에 데이터를 먼저 저장
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO, memberEntity);
        BoardEntity savedEntity = boardRepository.save(boardEntity);

        // 2. 파일이름 꺼내고, 저장용 이름 만들고 파일 로컬에 저장
        for (MultipartFile boardFile : boardDTO.getBoardFile()) {
            String originalFileName = boardFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
            String savePath = "C:\\project_img\\" + storedFileName;
            boardFile.transferTo(new File(savePath));
            // 3. BoardFileEntity로 변환 후 board_file_table에 저장
            // 자식 데이터를 저장할 때 반드시 부모의 id가 아닌 부모의 Entity 객체가 전달돼야 함.
            BoardFileEntity boardFileEntity =
                    BoardFileEntity.toSaveBoardFileEntity(savedEntity, storedFileName);
            boardFileRepository.save(boardFileEntity);
        }
        return savedEntity.getId();
    }

    @Transactional
    public List<BoardDTO> findAll(Long loginId) {
        List<BoardEntity> boardEntityList = boardRepository.findAllByFollowerIdOrMemberId(loginId);

        for (BoardEntity boardEntity: boardEntityList
        ) {
            System.out.println("boardEntity = " + boardEntity);
        }

        List<BoardDTO> boardDTOList = new ArrayList<>();
//        for (BoardEntity boardEntity: boardEntityList) {
//            boardDTOList.add(BoardDTO.toDTO(boardEntity));
//        }
        boardEntityList.forEach(boardEntity -> {
            List<BoardLikeEntity> boardLikeEntityList = boardLikeRepository.findByBoardEntity(boardEntity);
            int boardLikeCount = boardLikeEntityList.size();
            List<BoardCommentEntity> boardCommentEntityList = boardCommentRepository.findByBoardEntity(boardEntity);
            int boardCommentCount = boardCommentEntityList.size();
            LocalDateTime createdAt = boardEntity.getCreatedAt();
            String time = utilClass.timeForToday(createdAt);
            boardDTOList.add(BoardDTO.toMainDTO(boardEntity, boardLikeCount, boardCommentCount, time));
        });

        MemberEntity memberEntity = memberRepository.findById(loginId).orElseThrow(() -> new NoSuchElementException());
        List<BoardDTO> boardDTOList1 = new ArrayList<>();
        for (int i = 0; i < boardDTOList.size(); i++) {
            BoardDTO boardDTO = boardDTOList.get(i);
            BoardEntity boardEntity = boardRepository.findById(boardDTO.getId()).orElseThrow(() -> new NoSuchElementException());
            Optional<BoardLikeEntity> optionalBoardLikeEntity = boardLikeRepository.findByBoardEntityAndMemberEntity(boardEntity, memberEntity);
            Optional<BoardBookmarkEntity> optionalBoardBookmarkEntity = boardBookmarkRepository.findByBoardEntityAndMemberEntity(boardEntity, memberEntity);
            if (optionalBoardLikeEntity.isEmpty()) {
                boardDTO.setBoardLike(0);
            } else {
                boardDTO.setBoardLike(1);
            }
            if (optionalBoardBookmarkEntity.isEmpty()) {
                boardDTO.setBoardBookmark(0);
            } else {
                boardDTO.setBoardBookmark(1);
            }
            boardDTOList1.add(boardDTO);
        }
        return boardDTOList1;
    }

    @Transactional
    public List<BoardFileDTO> findAllFile() {
        List<BoardFileEntity> boardFileEntityList = boardFileRepository.findAll();
        List<BoardFileDTO> boardFileDTOList = new ArrayList<>();
        boardFileEntityList.forEach(boardFileEntity -> {
            boardFileDTOList.add(BoardFileDTO.toDTO(boardFileEntity));
        });
        return boardFileDTOList;
    }

    @Transactional
    public List<BoardDTO> findByMemberId(Long memberId) {
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException());
        List<BoardDTO> boardDTOList = new ArrayList<>();
        List<BoardEntity> boardEntityList = boardRepository.findByMemberEntityOrderByIdDesc(memberEntity);
        boardEntityList.forEach(boardEntity -> {
            List<BoardLikeEntity> boardLikeEntityList = boardLikeRepository.findByBoardEntity(boardEntity);
            int boardLikeCount = boardLikeEntityList.size();
            List<BoardCommentEntity> boardCommentEntityList = boardCommentRepository.findByBoardEntity(boardEntity);
            int boardCommentCount = boardCommentEntityList.size();
            boardDTOList.add(BoardDTO.toMyPageDTO(boardEntity, boardLikeCount, boardCommentCount));
        });
        return boardDTOList;
    }

//    @Transactional
//    public BoardDTO findById(Long id) {
//        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
//        BoardDTO boardDTO = BoardDTO.toDTO(boardEntity);
//        return boardDTO;
//    }

    @Transactional
    public BoardDTO findById(Long id) {
        BoardEntity boardEntity = boardRepository.findById(id).orElse(null);
        if (boardEntity == null) {
            return null;
        } else {
            LocalDateTime createdAt = boardEntity.getCreatedAt();
            String time = utilClass.timeForToday(createdAt);
            BoardDTO boardDTO = BoardDTO.toDTO2(boardEntity, time);
            return boardDTO;
        }
    }


    @Transactional
    public List<BoardFileDTO> findBoardFile(Long id) {
        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        List<BoardFileEntity> boardFileEntityList = boardFileRepository.findByBoardEntity(boardEntity);
        List<BoardFileDTO> boardFileDTOList = new ArrayList<>();
        boardFileEntityList.forEach(boardFileEntity -> {
            boardFileDTOList.add(BoardFileDTO.toDTO(boardFileEntity));
        });
        return boardFileDTOList;
    }

    @Transactional
    public List<BoardFileDTO> findAllFileOrderByDesc() {
        List<BoardFileEntity> boardFileEntityList = boardFileRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<BoardFileDTO> boardFileDTOList = new ArrayList<>();
        boardFileEntityList.forEach(boardFileEntity -> {
            boardFileDTOList.add(BoardFileDTO.toDTO(boardFileEntity));
        });
        return boardFileDTOList;
    }
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

//    public void update(BoardDTO boardDTO) {
//        // 로그인한 아이디의 memberDTO 가져오기
//        MemberEntity memberEntity = memberRepository.findById(boardDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
//        // 1. Board 테이블에 데이터를 먼저 저장
//        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO, memberEntity);
//        boardRepository.save(boardEntity);
//    }
public void update(BoardDTO boardDTO) {
    // 로그인한 회원 ID로 MemberEntity를 조회합니다.
    MemberEntity memberEntity = memberRepository.findById(boardDTO.getMemberId())
            .orElseThrow(() -> new NoSuchElementException("해당하는 회원을 찾을 수 없습니다."));

    // BoardDTO와 MemberEntity를 사용하여 BoardEntity를 생성합니다.
    BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO, memberEntity);

    // BoardEntity를 저장하거나 업데이트합니다.
    boardRepository.save(boardEntity);
}
}







