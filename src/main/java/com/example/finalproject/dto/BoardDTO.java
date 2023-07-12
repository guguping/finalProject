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
    private List<BoardDTO> followedMemberPosts;

    private List<String> storedFileName;

    private int boardLikeCount;
    private int boardCommentCount;


    public static BoardDTO toDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setMemberId(boardEntity.getMemberEntity().getId());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardBlind(boardEntity.getBoardBlind());
        boardDTO.setCreatedAt(UtilClass.dateFormat(boardEntity.getCreatedAt()));
        boardDTO.setBoardUpdate(boardEntity.getBoardUpdate());

        // 파일 조회
        List<String> storedFileNameList = new ArrayList<>();
        for (BoardFileEntity boardFileEntity : boardEntity.getBoardFileEntityList()) {
            storedFileNameList.add(boardFileEntity.getStoredFileName());
        }
        boardDTO.setStoredFileName(storedFileNameList);

        return boardDTO;
    }

    public static BoardDTO toMyPageDTO(BoardEntity boardEntity, int likeCount, int commentCount) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setMemberId(boardEntity.getMemberEntity().getId());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardBlind(boardEntity.getBoardBlind());
        boardDTO.setCreatedAt(UtilClass.dateFormat(boardEntity.getCreatedAt()));
        boardDTO.setBoardUpdate(boardEntity.getBoardUpdate());
        boardDTO.setBoardLikeCount(likeCount);
        boardDTO.setBoardCommentCount(commentCount);

        // 파일 조회
        List<String> storedFileNameList = new ArrayList<>();
        for (BoardFileEntity boardFileEntity : boardEntity.getBoardFileEntityList()) {
            storedFileNameList.add(boardFileEntity.getStoredFileName());
        }
        boardDTO.setStoredFileName(storedFileNameList);

        return boardDTO;
    }
}
