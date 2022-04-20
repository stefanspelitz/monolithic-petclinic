package org.springframework.samples.petclinic.service;

import org.springframework.samples.petclinic.db.OwnerRepository;
import org.springframework.samples.petclinic.db.RevenueRepository;
import org.springframework.samples.petclinic.db.VisitRepository;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.model.YearlyRevenue;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ClinicService {

    private final OwnerRepository owners;
    private final VisitRepository visits;
    private final RevenueRepository revenueRepository;

    public ClinicService(
        OwnerRepository owners,
        VisitRepository visits,
        RevenueRepository revenueRepository
    ) {
        this.owners = owners;
        this.visits = visits;
        this.revenueRepository = revenueRepository;
    }

    public Collection<Owner> ownerByLastName(String lastName) {
        return owners.findByLastName(lastName);
    }

    public Owner ownerById(int i) {
        return owners.findById(i);
    }


    public List<Visit> visitsByPetId(int petId) {
        return visits.findByPetId(petId);
    }

    public void save(Owner owner) {
        owners.save(owner);
    }

    public void save(Visit visit) {
        visits.save(visit);
    }

    public List<YearlyRevenue> listYearlyRevenue() {
        return revenueRepository.listYearlyRevenue();
    }
}
