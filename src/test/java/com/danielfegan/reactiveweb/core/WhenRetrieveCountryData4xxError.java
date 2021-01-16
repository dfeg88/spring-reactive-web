package com.danielfegan.reactiveweb.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.danielfegan.reactiveweb.util.TemplateUtil.templateErrorResponse;
import static org.springframework.http.MediaType.APPLICATION_JSON;

public class WhenRetrieveCountryData4xxError extends BaseTest {

    @BeforeAll
    public void setup() {
        stubRestCountriesClientResponseWithErrorResponseStatus.accept(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void returns_4xx_response_when_returned_by_client() {
        webTestClient.get()
            .uri(getEndpointUrl.apply(port))
            .accept(APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .is4xxClientError()
            .expectBody()
            .json(templateErrorResponse.apply(errorResponseBody, "Received 4xx response"));
    }

}
