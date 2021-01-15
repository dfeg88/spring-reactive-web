package com.danielfegan.reactiveweb.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class RestCountriesDataRepository implements CountriesDataRepository {

    private final WebClient webClient;

    @Override
    public Flux<RestCountriesClientResponse> retrieveAll() {
        return webClient
            .get()
            .uri("/all")
            .accept(APPLICATION_JSON)
            .retrieve()
            .bodyToFlux(RestCountriesClientResponse.class);
    }
}
