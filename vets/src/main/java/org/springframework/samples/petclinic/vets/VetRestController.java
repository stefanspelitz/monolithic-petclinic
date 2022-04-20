package org.springframework.samples.petclinic.vets;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VetRestController
{

    private final VetService vetService;

    public VetRestController( VetService vetService )
    {
        this.vetService = vetService;
    }

    @GetMapping("/vets2")
    public Collection<VetDTO> allVets() {
        return vetService.allVets();
    }
}
