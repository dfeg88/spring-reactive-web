package com.danielfegan.reactiveweb.core;

import com.danielfegan.reactiveweb.core.exception.CountriesDataException;
import com.danielfegan.reactiveweb.core.model.RestCountriesClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class CountriesDataRepository {

    private final WebClient webClient;

    public Flux<RestCountriesClientResponse> retrieveAll() {
        return webClient
            .get()
            .uri("/all")
            .accept(APPLICATION_JSON)
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError, r -> Mono.error(new CountriesDataException("Received 4xx response", r.statusCode())))
            .onStatus(HttpStatus::is5xxServerError, r -> Mono.error(new CountriesDataException("Received 5xx server error response", r.statusCode())))
            .bodyToFlux(RestCountriesClientResponse.class);
    }
}
