package com.travelog.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
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
    private List<String> hashtag;
    private boolean status;

}