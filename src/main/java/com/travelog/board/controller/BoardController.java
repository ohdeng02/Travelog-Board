package com.travelog.board.controller;

import com.travelog.board.dto.BoardReqDto;
import com.travelog.board.dto.BoardResDto;
import com.travelog.board.dto.PopularResInterface;
import com.travelog.board.entity.Board;
import com.travelog.board.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<PopularResInterface>> getPopular(){
        return new ResponseEntity<>(boardService.getPopular(), HttpStatus.OK);
    }

    // 글 작성
    @PostMapping(value = "/write")
    public ResponseEntity<BoardResDto> createdBoard(@Valid @RequestBody BoardReqDto boardReqDto){
        return new ResponseEntity<>(boardService.createBoard(boardReqDto), HttpStatus.CREATED);
    }

    // 글 삭제
    @DeleteMapping(value = "/{nickname}/{boardId}")
    public String deleteBoard(HttpServletRequest request, @PathVariable String nickname, @PathVariable long boardId){
        boardService.deleteBoard(boardId, nickname);
        String referer = request.getHeader("Referer");
        if(referer == null){
            referer = "/" + nickname;
        }
        return "redirect:" + referer;
    }

    // 글 수정
    @PutMapping(value = "/write/{boardId}")
    public Board updateBoard(@PathVariable long boardId, @RequestBody Board board){
        return boardService.upadateBoard(boardId, board);
    }

    // 게시글 조회
    @GetMapping(value = "/{nickname}/{boardId}")
    public ResponseEntity<BoardResDto> getBoard(@PathVariable String nickname, @PathVariable long boardId){
        return new ResponseEntity<>(boardService.readBoard(boardId, nickname), HttpStatus.OK);
    }
}
