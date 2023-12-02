package com.example.arendapro.exceptions.handler;

import com.example.arendapro.dto.ErrorResponse;
import com.example.arendapro.enums.ErrorCode;
import com.example.arendapro.exceptions.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ImageUploadException.class)
    public ResponseEntity<ErrorResponse> exceptionImageUploadHandler(ImageUploadException e){
        log.info(e.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .errorCode(404)
                .build();
        return ResponseEntity
                .badRequest()
                .body(errorResponse);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> exceptionInvalidArgumentHandler(MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;


    }

    @ExceptionHandler(UserAlreadyExistAuthenticationException.class)
    public ResponseEntity<ErrorResponse> exceptionUserAlreadyExistHandler(UserAlreadyExistAuthenticationException e){
        log.info(e.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .errorCode(ErrorCode.USER_EXISTS.getStatusCode())
                .build();
        return ResponseEntity
                .badRequest()
                .body(errorResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> exceptionAccessDeniedHandler(AccessDeniedException e) {
        log.info(e.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .errorCode(ErrorCode.ACCESS_DENIED.getStatusCode())
                .build();
        return ResponseEntity
                .badRequest()
                .body(errorResponse);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> exceptionUsernameNotFoundHandler(UsernameNotFoundException e) {
        log.info(e.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .errorCode(ErrorCode.USERNAME_NOT_FOUND.getStatusCode())
                .build();
        return ResponseEntity
                .badRequest()
                .body(errorResponse);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> exceptionEntityNotFoundHandler(EntityNotFoundException e) {
        log.info(e.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .errorCode(ErrorCode.ENTITY_NOT_FOUND.getStatusCode())
                .build();
        return ResponseEntity
                .badRequest()
                .body(errorResponse);
    }
    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ErrorResponse> exceptionPasswordMismatchHandler(PasswordMismatchException e) {
        log.info(e.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .errorCode(ErrorCode.PASSWORD_MISMATCH.getStatusCode())
                .build();
        return ResponseEntity
                .badRequest()
                .body(errorResponse);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> exceptionUserNotFoundHandler(UserNotFoundException e) {
        log.info(e.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .errorCode(ErrorCode.USER_NOT_FOUND.getStatusCode())
                .build();
        return ResponseEntity
                .badRequest()
                .body(errorResponse);
    }

}
