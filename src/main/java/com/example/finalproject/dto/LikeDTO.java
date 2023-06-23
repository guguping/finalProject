package com.example.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeDTO {
    private Long id;
    private Long LikeId;
    private Long memberId;
    private int LikeKind;


}
