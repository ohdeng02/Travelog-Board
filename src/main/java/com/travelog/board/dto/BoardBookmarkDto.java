package com.travelog.board.dto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;

@Data
public class BoardBookmarkDto {
    private String token;
    private Long boardId;

    public BoardBookmarkDto(String token, Long boardId) {
        this.token = token;
        this.boardId = boardId;
    }
}
