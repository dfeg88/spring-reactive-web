package com.danielfegan.reactiveweb.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CountriesDataException extends RuntimeException {

    private final HttpStatus status;

    public CountriesDataException(final String message, final HttpStatus status) {
        super(message);
        this.status = status;
    }

}
