package com.example.finalproject.serivce;

import com.example.finalproject.dto.BoardDTO;
import com.example.finalproject.dto.BoardFileDTO;
import com.example.finalproject.dto.MemberDTO;
import com.example.finalproject.entitiy.BoardEntity;
import com.example.finalproject.entitiy.BoardFileEntity;
import com.example.finalproject.entitiy.MemberEntity;
import com.example.finalproject.repository.BoardFileRepository;
import com.example.finalproject.repository.BoardRepository;
import com.example.finalproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;
    private final MemberRepository memberRepository;

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
    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
//        for (BoardEntity boardEntity: boardEntityList) {
//            boardDTOList.add(BoardDTO.toDTO(boardEntity));
//        }
        boardEntityList.forEach(boardEntity -> {
            boardDTOList.add(BoardDTO.toDTO(boardEntity));
        });
        return boardDTOList;
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

    public BoardDTO findByMemberId(Long memberId) {
     BoardEntity boardEntity = boardRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException());
        return BoardDTO.toDTO(boardEntity);
    }


}

