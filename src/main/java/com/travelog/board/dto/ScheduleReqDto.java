package com.travelog.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleReqDto {
    @NotBlank
    private LocalDate date;
    @NotBlank
    private String location;
    @NotBlank
    private Double latitude;
    @NotBlank
    private Double longitude;
    private String transport;
}
