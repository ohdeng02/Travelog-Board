package com.travelog.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
public class Comment {
    private Long id;

    private Long boardId;
    private String nickname;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private int report; //default 0
    private boolean status; //default 1
}
