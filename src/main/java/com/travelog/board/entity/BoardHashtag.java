package com.travelog.board.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class BoardHashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Hashtag hashtag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public BoardHashtag(Board board, Hashtag hashtag){
        this.board = board;
        this.hashtag = hashtag;
    }
}
