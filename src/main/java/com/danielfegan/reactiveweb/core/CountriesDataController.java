package com.danielfegan.reactiveweb.core;

import com.danielfegan.reactiveweb.core.model.CountriesDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/countries")
@RequiredArgsConstructor
public class CountriesDataController {

    private final CountriesDataService countriesDataService;

    @GetMapping
    public ResponseEntity<Mono<CountriesDataResponse>> getCountryData() {
        return ResponseEntity.ok(countriesDataService.retrieveCountryData());
    }

}
