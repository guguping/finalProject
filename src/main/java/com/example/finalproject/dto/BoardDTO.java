package com.example.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
