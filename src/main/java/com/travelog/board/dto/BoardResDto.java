package com.travelog.board.dto;

import com.travelog.board.entity.Board;
import com.travelog.board.entity.Comment;
import com.travelog.board.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardResDto {
    private Long boardId;
    private String nickname;
    private String local;
    private String title;
    private String contents;
    private String summary;

    private List<Schedule> schedules;
    private List<String> hashtags;

    private List<Comment> comments;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean status;
    private int views;

    // public BoardResDto (Board board){
    //     this.boardId = board.getBoardId();
    //     this.nickname = board.getNickname();
    //      //schedules = board.getSchedules();
    //     this.local = board.getLocal();
    //     this.title = board.getTitle();
    //     this.contents = board.getContents();
    //     this.summary = board.getSummary();
    //     this.createdAt = board.getCreatedAt();
    //     this.updatedAt = board.getUpdatedAt();
    //     this.views = board.getViews();

    // }

    public BoardResDto(Board board, List<Comment> comments){
        this.boardId = board.getBoardId();
        this.nickname = board.getNickname();
        this.local = board.getLocal();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.summary = board.getSummary();
        this.comments = comments;
        this.createdAt = board.getCreatedAt();
        this.updatedAt = board.getUpdatedAt();
        this.status = board.isStatus();
        this.views = board.getViews();
        this.schedules = board.getSchedules();
        this.hashtags = board.getHashtags().stream().map(o->o.getHashtag().getHashtag()).collect(Collectors.toList());
    }
}
