package com.douglas.Douglas.infrastructure.exception;

import com.douglas.Douglas.infrastructure.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class HandleException {

    private final MessageSource messageSource;

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<Response> businessExcpetion(BusinessException businessException) {
        return error(businessException.getMessage(),
                businessException.getInfo(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InternalAuthenticationServiceException.class)
    public ResponseEntity<Response> internalAuthenticationServiceException(InternalAuthenticationServiceException internalAuthenticationServiceException) {
        return error(internalAuthenticationServiceException.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Response> runtimeException(RuntimeException exception) {
        return error(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Response> error(String exception, HttpStatus httpStatus) {
            String message = messageSource.getMessage(exception,
                    null, LocaleContextHolder.getLocale());
        return new ResponseEntity<>(Response.builder().isSuccess(false).message(message).build()
                , httpStatus);
    }

    private ResponseEntity<Response> error(String exception, String info, HttpStatus httpStatus) {
        String message = messageSource.getMessage(exception,
                null, LocaleContextHolder.getLocale());
        if(Optional.ofNullable(info).isPresent()) message = String.format(message, info);
        log.error(message);
        return new ResponseEntity<>(Response.builder().isSuccess(false).message(message).build()
                , httpStatus);
    }

}
