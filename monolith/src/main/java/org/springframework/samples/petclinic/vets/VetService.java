package org.springframework.samples.petclinic.vets;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VetService implements IVetService {

    private final VetRepository vets;

    public VetService( VetRepository vets ) {
        this.vets = vets;
    }

    public Collection<VetDTO> allVets() {
        Collection<Vet> vets = this.vets.findAll();
        return vets.stream().map(this::convert).collect(Collectors.toList());
    }

    private VetDTO convert(Vet vet) {
        List<String> specialities = vet.getSpecialties().stream().map(specialty -> specialty.getName()).collect(Collectors.toList());
        return new VetDTO(vet.getId(), vet.getFirstName(), vet.getLastName(), specialities);
    }
}
