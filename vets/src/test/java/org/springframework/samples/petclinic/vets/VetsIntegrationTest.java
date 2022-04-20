package org.springframework.samples.petclinic.vets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

//@WebMvcTest(controllers = VetRestController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class VetsIntegrationTest
{
    @Autowired
    MockMvc mockMvc;

    @LocalServerPort
    int localServerPort;

    private final String baseUrl = "http://localhost:";

    @Test
    void receive_hello_world() {

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<VetDTO[]> response = testRestTemplate.
                getForEntity(baseUrl + localServerPort + "/vets2", VetDTO[].class);

        VetDTO[] vets = response.getBody();
        assertThat(vets).hasSize( 6 );

        assertThat(vets[2].getFirstName()).isEqualTo( "Linda" );
        assertThat(vets[2].getSpecialties()).containsExactly( "dentistry", "surgery" );

    }
}
