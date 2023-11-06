package com.travelog.board.dto;

import com.travelog.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardListResDto {
    private Long boardId;
    private String nickname;
    private String local;
    private String title;
    private String contents;
    private String summary;
    private List<ScheduleDto> schedules;
    private List<String> hashtags;
    private LocalDateTime createdAt;

    public BoardListResDto(Board board){
        this.boardId = board.getBoardId();
        this.nickname = board.getNickname();
        this.local = board.getLocal();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.summary = board.getSummary();
        this.schedules = board.getSchedules().stream().map(ScheduleDto::new).collect(Collectors.toList());
        this.hashtags = board.getHashtags().stream().map(o->o.getHashtag().getHashtag()).collect(Collectors.toList());
        this.createdAt = board.getCreatedAt();
    }
}
