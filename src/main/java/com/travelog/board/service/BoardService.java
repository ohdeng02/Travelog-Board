package com.travelog.board.service;

import com.travelog.board.entity.Board;
import com.travelog.board.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    
    // 글 작성
    @Transactional
    public Board createBoard(Board board){
        return boardRepository.save(board);
    }

    // 글 삭제
    @Transactional
    public void deleteBoard(long id, String nickname){
        boardRepository.deleteById(id);
    }
}
