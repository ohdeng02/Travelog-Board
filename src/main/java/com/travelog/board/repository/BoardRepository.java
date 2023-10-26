package com.travelog.board.repository;

import com.travelog.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface BoardRepository  extends JpaRepository<Board, Long> {
    @Query(value = "select * from board b where b.nickname = :nickname", nativeQuery = true)
    List<Board> findAllByName(@Param("nickname")String nickname);

    @Query(value = "select * from board b where b.local = :local", nativeQuery = true)
    List<Board> findAllByLocal(@Param("local")String local);

    @Query(value = "select * from board where status = true order by views desc limit 10", nativeQuery = true)
    List<Board> findPopular();

    Board findByBoardIdAndNickname(Long board_id, String nickname);

    @Query(value = "select * from board where title LIKE %:query% OR contents LIKE %:query%", nativeQuery = true)
    List<Board> findByTitleOrContentsContaining(@Param("query") String query);
}
