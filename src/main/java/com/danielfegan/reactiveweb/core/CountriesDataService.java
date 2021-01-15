package com.danielfegan.reactiveweb.core;

import com.danielfegan.reactiveweb.core.model.BordersPerCountry;
import com.danielfegan.reactiveweb.core.model.CountriesDataResponse;
import com.danielfegan.reactiveweb.core.model.CountryPopulation;
import com.danielfegan.reactiveweb.core.model.RestCountriesClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CountriesDataService {

    private final CountriesDataRepository countriesDataRepository;

    public Mono<CountriesDataResponse> retrieveCountryData() {
        return countriesDataRepository.retrieveAll()
            .collectList()
            .map(response -> {
                final List<BordersPerCountry> bordersPerCountries = response.stream()
                    .sorted(Comparator.comparingInt((RestCountriesClientResponse r) -> r.getBorders().size()).reversed())
                    .limit(5L)
                    .map(bpc -> new BordersPerCountry(bpc.getName(), bpc.getBorders().size()))
                    .collect(toList());

                final List<CountryPopulation> countryPopulations = response.stream()
                    .sorted(Comparator.comparingInt(RestCountriesClientResponse::getPopulation).reversed())
                    .limit(5L)
                    .map(cp -> new CountryPopulation(cp.getName(), cp.getPopulation()))
                    .collect(toList());

                return CountriesDataResponse.builder()
                    .countriesWithMostBorders(bordersPerCountries)
                    .countriesWithHighestPopulation(countryPopulations)
                    .build();
            });
    }
}
