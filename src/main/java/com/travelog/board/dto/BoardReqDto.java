package com.travelog.board.dto;

import jakarta.validation.constraints.NotBlank;
import com.travelog.board.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import com.travelog.board.entity.Board;
import com.travelog.board.entity.Hashtag;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardReqDto {
    @NotBlank
    private String nickname;
    @NotBlank
    private String local;
    @NotBlank
    private String title;
    @NotBlank
    private String contents;

    private String summary;
    private List<Schedule> schedules;
    private List<String> hashtags;
    private boolean status;
}


