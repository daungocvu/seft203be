package com.kms.seft203.config;

import com.kms.seft203.exception.ContactNotFoundException;
import com.kms.seft203.exception.DuplicatedEmailException;
import com.kms.seft203.exception.EmailNotFoundException;
import com.kms.seft203.exception.ErrorResponse;
import javassist.tools.web.BadHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    public static final String MODULE_NAME = "DashboardAPI module";

    @ExceptionHandler(DuplicatedEmailException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponse handlerDuplicatedEmailException(DuplicatedEmailException e) {
        log.error(MODULE_NAME + ": " + Arrays.toString(e.getStackTrace()));
        return new ErrorResponse(HttpStatus.NOT_ACCEPTABLE, HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage());
    }

    @ExceptionHandler(EmailNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerEmailNotFoundException(EmailNotFoundException e) {
        log.error(MODULE_NAME + ": " + Arrays.toString(e.getStackTrace()));
        return new ErrorResponse(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(ContactNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerContactNotFoundException(ContactNotFoundException e) {
        log.error(MODULE_NAME + ": " + Arrays.toString(e.getStackTrace()));
        return new ErrorResponse(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler(BadHttpRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerBadHttpRequest(BadHttpRequest e) {
        log.error(MODULE_NAME + ": " + Arrays.toString(e.getStackTrace()));
        return new ErrorResponse(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), e.toString());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handlerCommonException(Exception e) {
        log.error(MODULE_NAME + ": " + Arrays.toString(e.getStackTrace()));
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}