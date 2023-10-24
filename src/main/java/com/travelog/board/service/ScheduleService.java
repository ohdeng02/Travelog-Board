package com.travelog.board.service;

import com.travelog.board.dto.ScheduleDto;
import com.travelog.board.entity.Board;
import com.travelog.board.entity.Schedule;
import com.travelog.board.repository.ScheduleRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleService {

    @Autowired
    private final ScheduleRepository scheduleRepository;

    //일정 등록
    @Transactional
    public void connectSchedule(Board board){
        for(Schedule schedule : board.getSchedules()){
            schedule.setBoard(board);
            scheduleRepository.save(schedule);
        }
    }

    //일정 삭제
    @Transactional
    public void deleteSchedule(Long scheduleId){
        scheduleRepository.deleteById(scheduleId);
    }
}
