package com.example.finalproject.dto;

import com.example.finalproject.entitiy.BoardLikeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeDTO {
    private Long id;
    private Long boardId;
    private Long memberId;
    private int LikeKind;
}
