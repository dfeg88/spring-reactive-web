package com.danielfegan.reactiveweb.core;

import lombok.RequiredArgsConstructor;
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
    public Mono<CountriesDataResponse> getCountryData() {
        return countriesDataService.retrieveCountryData();
    }

}
