package com.danielfegan.reactiveweb.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CountriesDataService {

    private final CountriesDataRepository countriesDataRepository;

    public Mono<CountriesDataResponse> retrieveCountryData() {
        return countriesDataRepository.retrieveAll()
            .map(u -> new BordersPerCountry(u.getName(), u.getBorders().size()))
            .collectSortedList((a, b) -> b.getBorders().compareTo(a.getBorders()))
            .map(bpc -> bpc.stream().limit(5L).collect(toList()))
            .map(CountriesDataResponse::new);
    }
}
