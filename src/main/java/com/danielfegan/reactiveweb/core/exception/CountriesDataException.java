package com.danielfegan.reactiveweb.core.exception;

import lombok.Getter;

@Getter
public class CountriesDataException extends RuntimeException {

    private final int status;

    public CountriesDataException(final String message, final int status) {
        super(message);
        this.status = status;
    }

}
