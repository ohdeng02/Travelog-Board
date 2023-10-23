package com.travelog.board.dto;

import com.travelog.board.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto {
    private Long id;
    private Long boardId;
    private LocalDate date;
    private String location;
    private Double latitude;
    private Double longitude;

    public ScheduleDto(Schedule schedule){
        id = schedule.getId();
        boardId = schedule.getBoard().getBoardId();
        date = schedule.getDate();
        location = schedule.getLocation();
        latitude = schedule.getLatitude();
        longitude = schedule.getLongitude();
    }
}
