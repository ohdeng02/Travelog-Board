package com.travelog.board.repository;

import com.travelog.board.dto.PopularResInterface;
import com.travelog.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface BoardRepository  extends JpaRepository<Board, Long> {
    @Query(value = "select board_id as boardId, nickname, local, title, contents, summary from board order by views desc limit 10", nativeQuery = true)
    List<PopularResInterface> findPopular();

    Board findByBoardIdAndNickname(long board_id, String nickname);
}
