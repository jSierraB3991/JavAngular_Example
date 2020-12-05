package com.douglas.Douglas.infrastructure.exception;

import com.douglas.Douglas.infrastructure.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
public class HandleException extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<Response> businessExcpetion(BusinessException businessException) {
        return error(businessException.getMessage(),
                businessException.getInfo(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Response> exception(Exception exception) {
        return error(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Response> runtimeException(RuntimeException exception) {
        return error(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                        HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);

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
        return new ResponseEntity<>(Response.builder().isSuccess(false).message(message).build()
                , httpStatus);
    }

}
