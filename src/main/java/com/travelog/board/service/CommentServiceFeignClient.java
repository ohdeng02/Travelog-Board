package com.travelog.board.service;

import com.travelog.board.dto.CommentResDto;
import com.travelog.board.entity.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "comments")
public interface CommentServiceFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/comments/{nickname}/{boardId}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Comment> getComments(@PathVariable("nickname") String nickname, @PathVariable("boardId") Long boardId);
}
