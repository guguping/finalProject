package com.example.finalproject.dto;

import com.example.finalproject.entitiy.BoardEntity;
import com.example.finalproject.entitiy.BoardFileEntity;
import com.example.finalproject.util.UtilClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long id;
    private Long memberId;
    private String boardContents;
    private String createdAt;

    //    공개 여부 1 = 공개
    private int boardBlind = 1;

    //    업데이트 기록 1 = 수정
    private int boardUpdate = 0;

    private List<MultipartFile> boardFile;

    public static BoardDTO toDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setCreatedAt(UtilClass.dateFormat(boardEntity.getCreatedAt()));

        // 파일 여부에 따른 코드 추가
        // 파일 이름을 담을 리스트 객체 선언
        List<String> storedFileNameList = new ArrayList<>();
        // 첨부파일에 각각 접근
        for (BoardFileEntity boardFileEntity : boardEntity.getBoardFileEntityList()) {
            storedFileNameList.add(boardFileEntity.getStoredFileName());
        }
//        boardDTO.setStoredFileName(storedFileNameList);


        return boardDTO;
    }
}
