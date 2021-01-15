package com.danielfegan.reactiveweb.core;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;
import java.util.function.Function;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static wiremock.org.apache.commons.io.FileUtils.readFileToString;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestInstance(PER_CLASS)
@AutoConfigureWebTestClient
@AutoConfigureWireMock(port = 0)
@ActiveProfiles("test")
public class BaseTest {

    @LocalServerPort
    protected int port;

    @Value("classpath:json/rest-countries-response.json")
    private Resource restCountriesResponse;

    @Value("classpath:json/expected-response-body.json")
    protected Resource expectedResponseBody;

    @Autowired
    protected WebTestClient webTestClient;

    @AfterAll
    public void tearDown() {
        WireMock.reset();
    }

    protected void stubTheRestCountriesClientResponse() throws IOException {
        stubFor(get(urlPathMatching("/rest/v2/all"))
            .willReturn(okJson(readFileToString(restCountriesResponse.getFile())))
        );
    }

    protected final Function<Integer, String> getEndpointUrl = port -> "http://localhost:" + port + "/v1/countries";

}
