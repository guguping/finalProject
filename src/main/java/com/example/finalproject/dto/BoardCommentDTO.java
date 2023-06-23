package com.example.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardCommentDTO {
    private Long id;
    private Long boardId;
    private Long memberId;
    private String commentContents;
    private String createdAt;

//     0 = 비수정 / 1 = 수정
    private int commentUpdate = 0;
}
