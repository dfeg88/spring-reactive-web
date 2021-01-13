package com.danielfegan.reactiveweb.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class CountriesDataService {

    private final CountriesDataRepository countriesDataRepository;

    public Flux<?> retrieveCountryData() {
        return countriesDataRepository.retrieveAll();
    }
}
