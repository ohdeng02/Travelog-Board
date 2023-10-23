package com.travelog.board.dto;

import com.travelog.board.entity.Board;
import com.travelog.board.entity.BoardHashtag;
import com.travelog.board.entity.Hashtag;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardResDto {
    private long boardId;
    private String nickname;
    private String local;
    private String title;
    private String contents;
    private String summary;
    private Set<BoardHashtag> hashtags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean status;
    private int views;

    public BoardResDto(Board board, Set<BoardHashtag> hashtag){
        this.boardId = board.getBoardId();
        this.nickname = board.getNickname();
        this.local = board.getLocal();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.summary = board.getSummary();
        this.createdAt = board.getCreatedAt();
        this.updatedAt = board.getUpdatedAt();
        this.status = board.isStatus();
        this.views = board.getViews();
        this.hashtags = hashtag;
    }
}