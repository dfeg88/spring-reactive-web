package com.danielfegan.reactiveweb.core.exception.handler;

import com.danielfegan.reactiveweb.core.exception.CountriesDataException;
import com.danielfegan.reactiveweb.core.exception.ErrorResponseJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(CountriesDataException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseJson> handleCountriesDataException(final CountriesDataException e) {
        return new ResponseEntity<>(new ErrorResponseJson(e.getMessage()), HttpStatus.valueOf(e.getStatus()));
    }


}
