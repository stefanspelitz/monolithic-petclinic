package org.springframework.samples.petclinic;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.vets.VetDTO;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

public class WiremockDemoTest {

    private final WireMockServer wireMock = new WireMockServer(options().port(8089));

    @BeforeEach
    void startWireMock() {
        wireMock.start();
    }

    @AfterEach
    void stopWireMock() {
        wireMock.stop();
    }

    @Test
    void how_to_stub_a_server_with_wiremock() {
        wireMock.stubFor(get(urlEqualTo("/vets2"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("[{\"firstName\":\"James\",\"lastName\":\"Carter\",\"id\":1,\"specialties\":[],\"nrOfSpecialties\":0},{\"firstName\":\"Helen\",\"lastName\":\"Leary\",\"id\":2,\"specialties\":[\"radiology\"],\"nrOfSpecialties\":1},{\"firstName\":\"Linda\",\"lastName\":\"Douglas\",\"id\":3,\"specialties\":[\"dentistry\",\"surgery\"],\"nrOfSpecialties\":2},{\"firstName\":\"Rafael\",\"lastName\":\"Ortega\",\"id\":4,\"specialties\":[\"surgery\"],\"nrOfSpecialties\":1},{\"firstName\":\"Henry\",\"lastName\":\"Stevens\",\"id\":5,\"specialties\":[\"radiology\"],\"nrOfSpecialties\":1},{\"firstName\":\"Sharon\",\"lastName\":\"Jenkins\",\"id\":6,\"specialties\":[],\"nrOfSpecialties\":0}]")));

        VetDTO[] result = new RestTemplate().getForObject("http://localhost:8089/vets2", VetDTO[].class);
        assertThat(result).hasSize(6);
    }
}