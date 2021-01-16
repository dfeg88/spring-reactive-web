package com.danielfegan.reactiveweb.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.danielfegan.reactiveweb.util.TemplateUtil.templateErrorResponse;
import static org.springframework.http.MediaType.APPLICATION_JSON;

public class WhenRetrieveCountryData5xxError extends BaseTest {

    @BeforeAll
    public void setup() {
        stubRestCountriesClientResponseWithErrorResponseStatus.accept(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Test
    public void returns_4xx_response_when_returned_by_client() {
        webTestClient.get()
            .uri(getEndpointUrl.apply(port))
            .accept(APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .is5xxServerError()
            .expectBody()
            .json(templateErrorResponse.apply(errorResponseBody, "Received 5xx server error response"));
    }

}
