package com.pgf.politicalgovernanceframeworkbackend.handler;

import com.pgf.politicalgovernanceframeworkbackend.exception.EthException;
import com.pgf.politicalgovernanceframeworkbackend.exception.NotFoundException;
import com.pgf.politicalgovernanceframeworkbackend.fto.ErrorFto;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
        MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
