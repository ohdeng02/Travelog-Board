package com.travelog.board.service;

import com.travelog.board.dto.CommentResDto;
import com.travelog.board.entity.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("comments")
public interface CommentServiceFeignClient {
    @GetMapping(value = "/comments/{nickname}/{boardId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Comment> getComments(@PathVariable String nickname, Long boardId);
}
