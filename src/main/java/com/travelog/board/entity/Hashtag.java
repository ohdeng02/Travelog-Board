package com.travelog.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@ToString
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
    private Set<BoardHashtag> boards = new HashSet<BoardHashtag>();

    public void addBoard(BoardHashtag boardHashtag){
        this.boards.add(boardHashtag);
        boardHashtag.setHashtag(this);
    }

    @Builder
    public Hashtag(String hashtag){
        this.hashtag = hashtag;
    }
}
