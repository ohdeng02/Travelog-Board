package com.travelog.board.repository;

import com.travelog.board.entity.BoardHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardHashtagRepository extends JpaRepository<BoardHashtag, Long> {
    @Query(value = "select b.hashtag from " +
            "(select bh.*, h.hashtag from board_hashtag bh join fetch hashtag h on bh.hashtag_id = h.hashtag_id) b " +
            "where b.board_id = :boardId", nativeQuery = true)
    List<String> findAllHashtag(@Param("boardId") Long boardId);
}
