package com.jashan.borrow_borrow.error;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(UsernameNotFoundException exception){
        ApiError error=new ApiError("User Not Found "+exception.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error,error.getStatus());

    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException ex){
        ApiError error= new ApiError("Authentication failed "+ex.getMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(error,error.getStatus());
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> handleJwtException(JwtException ex){
        ApiError error= new ApiError("Invalid token "+ex.getMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(error,error.getStatus());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException ex){
        ApiError error= new ApiError("Access denied "+ex.getMessage(), HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(error,error.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception ex){
        ApiError error= new ApiError("Something went wrong "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(error,error.getStatus());
    }
}
