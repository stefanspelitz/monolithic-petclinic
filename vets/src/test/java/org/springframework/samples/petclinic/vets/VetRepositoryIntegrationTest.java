package org.springframework.samples.petclinic.vets;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VetRepositoryIntegrationTest
{

    @Autowired
    VetRepository vetsRepository;

    @Test
    void testFindVets() {
        Collection<Vet> all = vetsRepository.findAll();
        assertThat(all).hasSize(6);
    }
}
