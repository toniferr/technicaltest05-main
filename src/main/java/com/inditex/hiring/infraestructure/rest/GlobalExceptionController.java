package com.inditex.hiring.infraestructure.rest;

import com.inditex.hiring.infraestructure.exception.ApiErrorResponse;
import com.inditex.hiring.infraestructure.exception.NoSuchResourceFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(NoSuchResourceFoundException.class)
    public ResponseEntity<Object> handleNoSuchResource(final NoSuchResourceFoundException noSuchResourceFoundException) {

        final ApiErrorResponse apiErrorResponse = new ApiErrorResponse(404, noSuchResourceFoundException.getMessage());

        return ResponseEntity.status(404)
                .body(apiErrorResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegration(final DataIntegrityViolationException dataIntegrityViolationException) {

        final ApiErrorResponse apiErrorResponse = new ApiErrorResponse(409, dataIntegrityViolationException.getMessage());

        return ResponseEntity.status(409)
                .body(apiErrorResponse);
    }
}
