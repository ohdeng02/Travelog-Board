package com.travelog.board.controller;

import com.travelog.board.entity.Board;
import com.travelog.board.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class BoardController {
    @Autowired
    private final BoardService boardService;

    // 인기글 조회
    @GetMapping(value = "/")
    public List<Board> getPopular(){
        return boardService.getPopular();
    }

    // 글 작성
    @PostMapping(value = "/write")
    public Board createdBoard(@RequestBody Board board){
        return boardService.createBoard(board);
    }

    // 글 삭제
    @DeleteMapping(value = "/{nickname}/{boardId}")
    public void deleteBoard(@PathVariable String nickname,@PathVariable long boardId){
        boardService.deleteBoard(boardId, nickname);
    }
}
