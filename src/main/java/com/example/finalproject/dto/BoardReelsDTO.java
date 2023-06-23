package com.example.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardReelsDTO {
    private Long id;
    private Long memberId;
    private String reelsFile;
    private String createdAt;
    private String reelsContents;
}
