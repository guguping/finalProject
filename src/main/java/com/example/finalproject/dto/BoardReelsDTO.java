package com.example.finalproject.dto;

import com.example.finalproject.entitiy.BoardReelsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardReelsDTO {
    private Long id;
    private Long memberId;
    private String reelsFile;
    private String createdAt;
    private String reelsContents;
    private Long likeId;
    private Long bookId;
    private String memberProfile;
    private String memberNickName;



    public static BoardReelsDTO toDTO(BoardReelsEntity boardReelsEntity) {
        return BoardReelsDTO.builder()
                .id(boardReelsEntity.getId())
                .memberId(boardReelsEntity.getMemberEntity().getId())
                .reelsFile(boardReelsEntity.getReelsFile())
                .reelsContents(boardReelsEntity.getReelsContents())
                .build();
    }
}
