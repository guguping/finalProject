package com.example.finalproject.entitiy;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "board_reels_bookmark_table")
@Getter
@Setter
public class BoardReelsBookmarkEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reels_id")
    private BoardReelsEntity boardReelsEntity;
}
