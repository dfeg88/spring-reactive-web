package com.danielfegan.reactiveweb.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static java.nio.file.Files.readString;
import static org.springframework.http.MediaType.APPLICATION_JSON;

public class WhenRetrieveCountryDataIsSuccessful extends BaseTest {

    @BeforeAll
    public void setup() {
        stubTheRestCountriesClientResponse.run();
    }

    @Test
    public void returns_200_response_with_correct_response_body() throws IOException {
        webTestClient.get()
            .uri(getEndpointUrl.apply(port))
            .accept(APPLICATION_JSON)
            .exchange()
            .expectStatus().is2xxSuccessful()
            .expectBody().json(readString(expectedResponseBody.getFile().toPath()));
    }

}
