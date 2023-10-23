package com.travelog.board.dto;

import com.travelog.board.entity.Board;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<String> hashtags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean status;
    private int views;

    public BoardResDto(Board board, List<String> hashtag){
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