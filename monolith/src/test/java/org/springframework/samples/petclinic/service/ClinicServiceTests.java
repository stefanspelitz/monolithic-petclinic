/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.model.YearlyRevenue;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ken Krebs
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 * @author Dave Syer
 */
@SpringBootTest
class ClinicServiceTests {

    @Autowired
    ClinicService service;

    @Test
    void shouldFindOwnersByLastName() {
        Collection<Owner> owners = service.ownerByLastName("Davis");
        assertThat(owners).hasSize(2);

        owners = service.ownerByLastName("Daviss");
        assertThat(owners).isEmpty();
    }

    @Test
    void shouldFindSingleOwnerWithPet() {
        Owner owner = service.ownerById(1);
        assertThat(owner.getLastName()).startsWith("Franklin");
        assertThat(owner.getPets()).hasSize(1);
        assertThat(owner.getPets().get(0).getType()).isNotNull();
        assertThat(owner.getPets().get(0).getType().getName()).isEqualTo("cat");
    }

    @Test
    @Transactional
    void shouldInsertOwner() {
        Collection<Owner> owners = service.ownerByLastName("Schultz");
        int found = owners.size();

        Owner owner = new Owner();
        owner.setFirstName("Sam");
        owner.setLastName("Schultz");
        owner.setAddress("4, Evans Street");
        owner.setCity("Wollongong");
        owner.setTelephone("4444444444");
        service.save(owner);
        assertThat(owner.getId().longValue()).isNotEqualTo(0);

        owners = service.ownerByLastName("Schultz");
        assertThat(owners.size()).isEqualTo(found + 1);
    }

    @Test
    @Transactional
    void shouldUpdateOwner() {
        Owner owner = service.ownerById(1);
        String oldLastName = owner.getLastName();
        String newLastName = oldLastName + "X";

        owner.setLastName(newLastName);
        service.save(owner);

        // retrieving new name from database
        owner = service.ownerById(1);
        assertThat(owner.getLastName()).isEqualTo(newLastName);
    }


    @Test
    void shouldFindVisitsByPetId() {
        Collection<Visit> visits = service.visitsByPetId(7);

        assertThat(visits).hasSize(2);
        Visit[] visitArr = visits.toArray(new Visit[0]);
        assertThat(visitArr[0].getDate()).isNotNull();
        assertThat(visitArr[0].getPetId()).isEqualTo(7);
        assertThat(visitArr[0].getCost()).isEqualTo(100);
    }

    @Test
    void shouldListYearlyRevenue() {
        List<YearlyRevenue> yearlyRevenues = service.listYearlyRevenue();

        assertThat(yearlyRevenues).hasSize(1);
        assertThat(yearlyRevenues.get(0).getTotal()).isEqualTo(800L);
    }


}
