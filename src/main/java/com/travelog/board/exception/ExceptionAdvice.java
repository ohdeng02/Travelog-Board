package com.travelog.board.exception;

import com.travelog.board.dto.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<?> illegalArgumentExceptionAdvice(IllegalArgumentException e){
        String msg = "null";
        return new ResponseEntity<>(CMRespDto.builder()
                .isSuccess(false).msg(e.getMessage().toString())
                .body(msg).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
