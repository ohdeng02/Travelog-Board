package com.travelog.board.controller;

import com.travelog.board.dto.CMRespDto;
import com.travelog.board.dto.ScheduleDto;
import com.travelog.board.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/")
public class ScheduleController {
    @Autowired
    private final ScheduleService scheduleService;


}
