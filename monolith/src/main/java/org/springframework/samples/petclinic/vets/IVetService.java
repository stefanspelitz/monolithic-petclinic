package org.springframework.samples.petclinic.vets;

import java.util.Collection;

public interface IVetService
{
    Collection<VetDTO> allVets();
}
