package com.travelog.board.service;

import com.travelog.board.dto.BoardReqDto;
import com.travelog.board.dto.BoardResDto;
import com.travelog.board.dto.PopularResInterface;
import com.travelog.board.entity.Board;
import com.travelog.board.entity.BoardHashtag;
import com.travelog.board.entity.Hashtag;
import com.travelog.board.repository.BoardHashtagRepository;
import com.travelog.board.repository.BoardRepository;
import com.travelog.board.repository.HashtagRepository;
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
    @Autowired
    private final HashtagRepository hashtagRepository;
    @Autowired
    private final BoardHashtagRepository boardHashtagRepository;

    // 인기글 조회
    @Transactional(readOnly = true)
    public List<PopularResInterface> getPopular(){
        return boardRepository.findPopular();
    }

    //블로그 게시글 목록 조회
    @Transactional(readOnly = true)
    public List<BoardResDto> getBlogHome(String nickname){
        List<Board> boards = boardRepository.findAllByName(nickname);
        return boards.stream()
                .map(BoardResDto::new).collect(Collectors.toList());
    }

    //지역별 게시글 목록 조회
    @Transactional(readOnly = true)
    public List<BoardResDto> getLocalSearch(String local) {
        List<Board> boards = boardRepository.findAllByLocal(local);
        return boards.stream()
                .map(BoardResDto::new).collect(Collectors.toList());
    }

    // 게시글 조회(조회수 증가)
    @Transactional
    public BoardResDto readBoard(long id, String nickname){
        Board board = boardRepository.findByBoardIdAndNickname(id, nickname);
        board.updateViews(board.getViews()+1);
        List<String> hashtags = new ArrayList<>();
        for(BoardHashtag hashtag: board.getHashtags()){
            hashtags.add(hashtag.getHashtag().getHashtag());
        } //성능에 문제를 발생시키지 않을까?
        return new BoardResDto(board, hashtags);
    }
    
    // 글 작성 (db 접근이 조금 많이 이루어지는 것 같아 간추려지면 더 좋을 것 같습니당)
    @Transactional
    public Board createBoard(BoardReqDto boardReqDto){
        Board board = Board.builder()
                .nickname(boardReqDto.getNickname())
                .local(boardReqDto.getLocal())
                .title(boardReqDto.getTitle())
                .contents(boardReqDto.getContents())
                .summary(boardReqDto.getSummary())
                .schedules(boardReqDto.getSchedules())
                .status(boardReqDto.isStatus())
                .build();
        Board board1 = boardRepository.save(board);

        // hashtag 테이블에 검색 후 없으면 저장
        // board_hashtag 테이블에 보드 아이디, 해시태그 아이디 저장
        for (String hashtag: boardReqDto.getHashtag()){
            Hashtag hashtag1 = hashtagRepository.findByHashtag(hashtag)
                    .orElseGet(()-> hashtagRepository.save(Hashtag.builder()
                            .hashtag(hashtag)
                            .build()));

            BoardHashtag boardHashtag = new BoardHashtag();

            board1.addHashtag(boardHashtag);
            hashtag1.addBoard(boardHashtag);

            boardHashtagRepository.save(boardHashtag);
        }

        return board1;
    }

    // 글 삭제
    @Transactional
    public void deleteBoard(long id, String nickname){
        Board board = boardRepository.findByBoardIdAndNickname(id, nickname);

        //hashtag의 boards에서 해당 게시글 삭제
        for(BoardHashtag boardHashtag : board.getHashtags()){
            Optional<Hashtag> hashtag = hashtagRepository.findByHashtag(boardHashtag.getHashtag().getHashtag());
            if(hashtag.isPresent()) {
                hashtag.get().getBoards().remove(boardHashtag);
            } else {
                throw new NoSuchElementException();
            }
        }
        boardRepository.delete(board);
    }

    // 글 수정( 수정 필요 )
    @Transactional
    public Board upadateBoard(long id, Board boardA){
        Board boardB = boardRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("해당 게시글이 존재하지 않습니다."));
        boardB.updateBoard(boardA);

        return boardB;
    }
}
