package com.pgf.politicalgovernanceframeworkbackend.handler;

import com.pgf.politicalgovernanceframeworkbackend.exception.EthException;
import com.pgf.politicalgovernanceframeworkbackend.exception.NotFoundException;
import com.pgf.politicalgovernanceframeworkbackend.fto.ErrorFto;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlingController {


    @ExceptionHandler({NotFoundException.class, EthException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorFto notFoundExceptionHandler(NotFoundException ex) {
        log.error(ex.getMessage());
        return ErrorFto.builder()
            .statusCode(HttpStatus.NOT_FOUND.value())
            .message(ex.getMessage())
            .timestamp(new Date()).build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorFto globalExceptionHandler(Exception ex) {
        log.error(ex.getMessage());
        return ErrorFto.builder()
            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message(ex.getMessage())
            .timestamp(new Date()).build();
    }
}
