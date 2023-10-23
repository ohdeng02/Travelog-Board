package com.travelog.board.service;

import com.travelog.board.dto.BoardResDto;
import com.travelog.board.entity.Board;
import com.travelog.board.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BoardService {
    @Autowired
    private final BoardRepository boardRepository;

    // 인기글 조회
    @Transactional(readOnly = true)
    public List<Board> getPopular(){
        return boardRepository.findPopular();
    }

    //블로그 게시글 목록 조회
    @Transactional(readOnly = true)
    public List<BoardResDto> getBlogHome(String nickname){
        List<Board> boards = boardRepository.findAllByName(nickname);
        return boards.stream()
                .map(BoardResDto::new).collect(Collectors.toList());
    }

    //지역별 게시글 조회
    @Transactional(readOnly = true)
    public List<BoardResDto> getLocalSearch(String local) {
        List<Board> boards = boardRepository.findAllByLocal(local);
        return boards.stream()
                .map(BoardResDto::new).collect(Collectors.toList());
    }

    //글 조회
    @Transactional
    public Board getBoard(Long boardId){
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 id입니다."));
    }
    
    // 글 작성
    @Transactional
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    // 글 삭제
    @Transactional
    public void deleteBoard(long id, String nickname){
        boardRepository.deleteById(id);
    }
}
