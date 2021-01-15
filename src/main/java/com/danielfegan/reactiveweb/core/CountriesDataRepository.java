package com.danielfegan.reactiveweb.core;

import reactor.core.publisher.Flux;

public interface CountriesDataRepository {
    Flux<RestCountriesClientResponse> retrieveAll();
}
