package com.example.finalproject.dto;

import com.example.finalproject.entitiy.BoardReelsBookmarkEntity;
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
    private Long memberId;
    private String createdAt;

    public static BookmarkDTO reelsToDTO(BoardReelsBookmarkEntity save) {
        BookmarkDTO bookmarkDTO = new BookmarkDTO();
        bookmarkDTO.setId(save.getId());
        bookmarkDTO.setReelsId(save.getBoardReelsEntity().getId());
        bookmarkDTO.setMemberId(save.getMemberEntity().getId());
        return bookmarkDTO;
    }
}
