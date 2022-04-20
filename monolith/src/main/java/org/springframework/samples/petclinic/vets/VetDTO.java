package org.springframework.samples.petclinic.vets;

import java.util.List;

public class VetDTO {
    private String firstName;
    private String lastName;
    private Integer id;

    private List<String> specialties;

    public VetDTO(Integer id, String firstName, String lastName, List<String> specialties) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialties = specialties;
    }

    public List<String> getSpecialties() {
        return specialties;
    }

    public int getNrOfSpecialties() {
        return specialties.size();
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
