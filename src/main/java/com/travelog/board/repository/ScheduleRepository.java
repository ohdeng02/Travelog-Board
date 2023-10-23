package com.travelog.board.repository;

import com.travelog.board.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository  extends JpaRepository<Schedule, Long> {
    @Query(value = "select s from Schedule s")
    List<Schedule> findAll();
    @Query(value = "select * from schedule s where s.board_id = :boardId", nativeQuery = true)
    List<Schedule> findAllByBoardId(@Param("boardId") Long boardId);
}
