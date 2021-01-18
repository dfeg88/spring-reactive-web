package com.danielfegan.reactiveweb.core.exception.handler;

import com.danielfegan.reactiveweb.core.exception.CountriesDataException;
import com.danielfegan.reactiveweb.core.exception.ErrorResponseJson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(CountriesDataException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseJson> handleCountriesDataException(final CountriesDataException e) {
        return ResponseEntity.status(e.getStatus()).body(new ErrorResponseJson(e.getMessage()));
    }

}
