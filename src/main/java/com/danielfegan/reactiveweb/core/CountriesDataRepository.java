package com.danielfegan.reactiveweb.core;

import com.danielfegan.reactiveweb.core.model.RestCountriesClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;

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
            .bodyToFlux(RestCountriesClientResponse.class)
            .cache(Duration.ofSeconds(10L))
            .log();
    }
}
