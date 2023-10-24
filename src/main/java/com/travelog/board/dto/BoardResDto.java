package com.travelog.board.dto;

import com.travelog.board.entity.Board;
import com.travelog.board.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.travelog.board.entity.BoardHashtag;
import com.travelog.board.entity.Hashtag;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardResDto {
    private Long boardId;
    private String nickname;
    private List<Schedule> schedules;

    private String local;
    private String title;
    private String contents;
    private String summary;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> hashtags;
    private boolean status;
    private int views;

     public BoardResDto (Board board){
         boardId = board.getBoardId();
         nickname = board.getNickname();
         //schedules = board.getSchedules();
         local = board.getLocal();
         title = board.getTitle();
         contents = board.getContents();
         summary = board.getSummary();
         createdAt = board.getCreatedAt();
         updatedAt = board.getUpdatedAt();
         views = board.getViews();
     }

    public BoardResDto(Board board, List<String> hashtag){
        this.boardId = board.getBoardId();
        this.nickname = board.getNickname();
        this.local = board.getLocal();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.summary = board.getSummary();
        this.schedules = board.getSchedules();
        this.createdAt = board.getCreatedAt();
        this.updatedAt = board.getUpdatedAt();
        this.status = board.isStatus();
        this.views = board.getViews();
        this.hashtags = hashtag;
    }
}
