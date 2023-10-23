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
    
    // 글 작성
    @Transactional
    public BoardResDto createBoard(BoardReqDto boardReqDto){
        Board board = Board.builder()
                .nickname(boardReqDto.getNickname())
                .local(boardReqDto.getLocal())
                .title(boardReqDto.getTitle())
                .contents(boardReqDto.getContents())
                .summary(boardReqDto.getSummary())
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

            BoardHashtag boardHashtag = new BoardHashtag(board1, hashtag1);
            board1.getHashtags().add(boardHashtag);
            hashtag1.getBoards().add(boardHashtag);

            boardHashtagRepository.save(boardHashtag);
        }

        return new BoardResDto(board1, boardReqDto.getHashtag());
    }

    // 글 삭제
    @Transactional
    public void deleteBoard(long id, String nickname){
        Board board = boardRepository.findByBoardIdAndNickname(id, nickname);
        boardRepository.delete(board);

        //hashtag의 boards에서 해당 게시글 삭제
        for(BoardHashtag boardHashtag : board.getHashtags()){
            Optional<Hashtag> hashtag = hashtagRepository.findByHashtag(boardHashtag.getHashtag().getHashtag());
            if(hashtag.isPresent()) {
                hashtag.get().getBoards().remove(boardHashtag);
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    // 글 수정( 수정 필요 )
    @Transactional
    public Board upadateBoard(long id, Board boardA){
        Board boardB = boardRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("해당 게시글이 존재하지 않습니다."));
        boardB.updateBoard(boardA);

        return boardB;
    }

    // 게시글 조회(조회수 증가)
    @Transactional
    public BoardResDto readBoard(long id, String nickname){
        Board board = boardRepository.findByBoardIdAndNickname(id, nickname);
        board.updateViews(board.getViews()+1);

        // 해시태그 가져오기
        List<String> hashtag = new ArrayList<>();
        for(BoardHashtag boardHashtag:board.getHashtags()){
            hashtag.add(boardHashtag.getHashtag().getHashtag());
        }
        return new BoardResDto(board, hashtag);
    }
}
