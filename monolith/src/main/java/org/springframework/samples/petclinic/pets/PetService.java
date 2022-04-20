package org.springframework.samples.petclinic.pets;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PetService
{

    private final PetRepository pets;

    public PetService( PetRepository pets )
    {
        this.pets = pets;
    }

    public Pet petById(int id) {
        return pets.findById(id);
    }

    public List<PetType> petTypes() {
        return pets.findPetTypes();
    }

    public void save(Pet pet) {
        pets.save(pet);
    }
}
