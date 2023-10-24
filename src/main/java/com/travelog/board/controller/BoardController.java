package com.travelog.board.controller;

import com.travelog.board.dto.CMRespDto;
import com.travelog.board.dto.BoardReqDto;
import com.travelog.board.dto.BoardResDto;
import com.travelog.board.dto.PopularResInterface;
import com.travelog.board.entity.Board;
import com.travelog.board.service.BoardService;
import com.travelog.board.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Autowired
    private final ScheduleService scheduleService;

    // 인기글 조회
    @GetMapping(value = "/")
    public ResponseEntity<List<PopularResInterface>> getPopular(){
        return new ResponseEntity<>(boardService.getPopular(), HttpStatus.OK);
    }

    //블로그 게시글 목록 조회 OK
    @Tag(name = "블로그 홈 조회")
    @Operation(summary = "개인 홈 조회", description = "nickname을 이용해 board 목록을 조회합니다.")
    @GetMapping(value = "/{nickname}")
    public ResponseEntity<?> getBlogHome(@PathVariable String nickname){
        List<BoardResDto> dtos = boardService.getBlogHome(nickname);
        return new ResponseEntity<>(CMRespDto.builder()
                .isSuccess(true).msg("블로그 게시글 목록이 조회되었습니다.").body(dtos).build(), HttpStatus.OK);
    }

    //지역별 게시글 검색 OK
    @GetMapping(value = "/local/{local}")
    public ResponseEntity<?> getLocalSearch(@PathVariable String local){
        List<BoardResDto> dtos = boardService.getLocalSearch(local);
        return new ResponseEntity<>(CMRespDto.builder()
                .isSuccess(true).msg("지역별 검색 목록이 조회되었습니다.").body(dtos).build(), HttpStatus.OK);
    }

    // 글 조회 OK
    @GetMapping(value = "/{nickname}/{boardId}")
    public ResponseEntity<?> getBoard(@PathVariable String nickname, @PathVariable Long boardId){
        BoardResDto board =  boardService.readBoard(boardId, nickname);
        return new ResponseEntity<>(CMRespDto.builder()
                .isSuccess(true).msg("게시글이 조회되었습니다.").body(board).build(), HttpStatus.OK);
    }

    //글 생성 + 일정 생성 OK
    @PostMapping(value = "/write")
    public ResponseEntity<?> createdBoard(@Valid @RequestBody BoardReqDto boardReqDto){
        Board res = boardService.createBoard(boardReqDto);
        scheduleService.connectSchedule(res);
        return new ResponseEntity<>(CMRespDto.builder().isSuccess(true).msg("게시물 저장완료")
                    .body("board_id: "+res.getBoardId()).build(), HttpStatus.CREATED);
    }

    // 글 삭제 OK
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{nickname}/{boardId}")
    public String deleteBoard(HttpServletRequest request, @PathVariable String nickname, @PathVariable Long boardId){
        boardService.deleteBoard(boardId, nickname);
        String referer = request.getHeader("Referer");
        if(referer == null){
            referer = "/" + nickname;
        }
        return "redirect:" + referer;
    }

    // 글 수정
    @PutMapping(value = "/write/{boardId}")
    public Board updateBoard(@PathVariable Long boardId, @RequestBody Board board){
        return boardService.upadateBoard(boardId, board);
    }
}
