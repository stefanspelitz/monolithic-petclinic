package org.springframework.samples.petclinic.vets;

import java.io.Serializable;
import java.util.List;

public class VetDTO implements Serializable
{
    private String firstName;
    private String lastName;
    private Integer id;

    private List<String> specialties;

    public VetDTO() {

    }

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

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public void setId( Integer id )
    {
        this.id = id;
    }

    public void setSpecialties( List<String> specialties )
    {
        this.specialties = specialties;
    }
}
