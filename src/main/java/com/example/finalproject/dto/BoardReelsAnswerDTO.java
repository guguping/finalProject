package com.example.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardReelsAnswerDTO {
    private Long id;
    private Long commentId;
    private Long memberId;
    private String answerContents;
    private String createdAt;
}
