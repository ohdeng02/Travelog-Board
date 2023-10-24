package com.travelog.board.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;

@ToString(exclude = "boards")
@Entity
@Table
@Getter
@NoArgsConstructor
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long hashtagId;

    @Column(nullable = false)
    private String hashtag;

    @OneToMany(mappedBy = "hashtag", fetch = FetchType.LAZY)
    private Set<BoardHashtag> boards = new HashSet<>();

//    public void addBoard(BoardHashtag boardHashtag){
//        boardHashtag.setHashtag(this);
//        this.boards.add(boardHashtag);
//    }

    @Builder
    public Hashtag(String hashtag){
        this.hashtag = hashtag;
    }
}
