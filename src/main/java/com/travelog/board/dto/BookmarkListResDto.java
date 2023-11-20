package com.travelog.board.dto;

import com.travelog.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkListResDto {
    private Long boardId;
    private String nickname;
    private String local;
    private String title;
    private String contents;
    private String summary;
    private List<String> hashtags;
    private LocalDateTime createdAt;

    public BookmarkListResDto(Board board){
        this.boardId = board.getBoardId();
        this.nickname = board.getNickname();
        this.local = board.getLocal();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.summary = board.getSummary();
        this.hashtags = board.getHashtags().stream().map(o->o.getHashtag().getHashtag()).collect(Collectors.toList());
        this.createdAt = board.getCreatedAt();
    }
}
