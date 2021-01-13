package com.danielfegan.reactiveweb.core;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/v1/countries")
@RequiredArgsConstructor
public class CountriesDataController {

    private final CountriesDataService countriesDataService;

    @GetMapping
    public Flux<?> getCountryData() {
        return countriesDataService.retrieveCountryData();
    }

}
