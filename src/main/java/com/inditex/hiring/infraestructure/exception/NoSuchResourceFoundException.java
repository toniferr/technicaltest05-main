package com.inditex.hiring.infraestructure.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchResourceFoundException extends RuntimeException {

    public NoSuchResourceFoundException(){}

	public NoSuchResourceFoundException(String msg) {
        super(msg);
    }
}
