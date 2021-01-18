package com.danielfegan.reactiveweb.core;

import com.danielfegan.reactiveweb.core.model.BordersPerCountry;
import com.danielfegan.reactiveweb.core.model.CountriesDataResponse;
import com.danielfegan.reactiveweb.core.model.CountryPopulation;
import com.danielfegan.reactiveweb.core.model.RestCountriesClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CountriesDataService {

    private final CountriesDataRepository countriesDataRepository;

    private final Function<List<RestCountriesClientResponse>, List<CountryPopulation>> getHighestPopulations = r ->
        r.stream()
            .sorted(comparingInt(RestCountriesClientResponse::getPopulation).reversed())
            .limit(5L)
            .map(cp -> new CountryPopulation(cp.getName(), cp.getPopulation()))
            .collect(toList());

    private final Function<List<RestCountriesClientResponse>, List<BordersPerCountry>> getBordersPerCountries = r ->
        r.stream()
            .sorted(comparingInt((RestCountriesClientResponse c) -> c.getBorders().size()).reversed())
            .limit(5L)
            .map(bpc -> new BordersPerCountry(bpc.getName(), bpc.getBorders().size()))
            .collect(toList());

    public Mono<CountriesDataResponse> retrieveCountryData() {
        return countriesDataRepository.retrieveAll()
            .collectList()
            .map(response -> CountriesDataResponse.builder()
                .countriesWithMostBorders(getBordersPerCountries.apply(response))
                .countriesWithHighestPopulation(getHighestPopulations.apply(response))
                .build()
            );
    }

}
