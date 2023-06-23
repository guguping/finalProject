package com.example.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkDTO {
    private Long id;
    private Long boardId;
    private Long reelsId;
    private String createdAt;
}
