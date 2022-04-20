package org.springframework.samples.petclinic.vets;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Qualifier("rest")
public class VetRestClient implements IVetService
{

    private RestTemplate restTemplate;
    private final String url = "http://localhost:8081/vets2";

    @Override
    public Collection<VetDTO> allVets()
    {
        if (restTemplate == null)
            restTemplate = new RestTemplate();
        VetDTO[] vets = restTemplate.getForEntity(url, VetDTO[].class).getBody();
        return Arrays.asList( vets );
    }
}
