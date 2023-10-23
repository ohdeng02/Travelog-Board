package com.travelog.board.dto;

import com.travelog.board.entity.Board;
import com.travelog.board.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardResDto {
    private Long boardId;
    private String nickname;
    //private List<Schedule> schedules;
    private String local;
    private String title;
    private String contents;
    private String summary;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
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
}

