package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samples.petclinic.base.BaseEntity;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.pets.Pet;
import org.springframework.samples.petclinic.pets.PetService;
import org.springframework.samples.petclinic.pets.PetType;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class PetServiceTests
{
    @Autowired
    PetService service;

    @Autowired
    ClinicService clinicService;

    @Test
    void shouldFindPetWithCorrectId() {
        Pet pet7 = service.petById(7);
        assertThat(pet7.getName()).startsWith("Samantha");
        assertThat(pet7.getOwner().getFirstName()).isEqualTo("Jean");
    }

    @Test
    void shouldFindAllPetTypes() {
        Collection<PetType> petTypes = service.petTypes();

        PetType petType1 = getById(petTypes, PetType.class, 1);
        assertThat(petType1.getName()).isEqualTo("cat");
        PetType petType4 = getById(petTypes, PetType.class, 4);
        assertThat(petType4.getName()).isEqualTo("snake");
    }

    @Test
    @Transactional
    void shouldInsertPetIntoDatabaseAndGenerateId() {
        Owner owner6 = clinicService.ownerById(6);
        int found = owner6.getPets().size();

        Pet pet = new Pet();
        pet.setName("bowser");
        Collection<PetType> types = service.petTypes();
        pet.setType(getById(types, PetType.class, 2));
        pet.setBirthDate( LocalDate.now());
        owner6.addPet(pet);
        assertThat(owner6.getPets().size()).isEqualTo(found + 1);

        service.save(pet);
        clinicService.save(owner6);

        owner6 = clinicService.ownerById(6);
        assertThat(owner6.getPets().size()).isEqualTo(found + 1);
        // checks that id has been generated
        assertThat(pet.getId()).isNotNull();
    }

    @Test
    @Transactional
    void shouldUpdatePetName() {
        Pet pet7 = service.petById(7);
        String oldName = pet7.getName();

        String newName = oldName + "X";
        pet7.setName(newName);
        service.save(pet7);

        pet7 = service.petById(7);
        assertThat(pet7.getName()).isEqualTo(newName);
    }


    @Test
    @Transactional
    void shouldAddNewVisitForPet() {
        Pet pet7 = service.petById(7);
        int found = pet7.getVisits().size();
        Visit visit = new Visit();
        pet7.addVisit(visit);
        visit.setDescription("test");
        visit.setCost(100);
        clinicService.save(visit);
        service.save(pet7);

        pet7 = service.petById(7);
        assertThat(pet7.getVisits().size()).isEqualTo(found + 1);
        assertThat(visit.getId()).isNotNull();
    }

    /**
     * Look up the entity of the given class with the given id in the given collection.
     *
     * @param entities    the collection to search
     * @param entityClass the entity class to look up
     * @param entityId    the entity id to look up
     * @return the found entity
     * @throws ObjectRetrievalFailureException if the entity was not found
     */
    private <T extends BaseEntity> T getById(Collection<T> entities, Class<T> entityClass, int entityId)
            throws ObjectRetrievalFailureException {
        for (T entity : entities) {
            if (entity.getId() == entityId && entityClass.isInstance(entity)) {
                return entity;
            }
        }
        throw new ObjectRetrievalFailureException(entityClass, entityId);
    }
}
