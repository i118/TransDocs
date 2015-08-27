package com.td.model.listener;

import com.td.model.entity.dictionary.company.Contractor;
import com.td.model.entity.dictionary.company.ContractPerson;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Created by konstantinchipunov on 01.09.14.
 */
public class ContractorListener {
    @PrePersist
    public void persist(Contractor contractor) {
        if (contractor.getPersons() == null) return;
        contractor.getPersons().parallelStream().forEach((ContractPerson person) -> {
            if (person.getContractor() == null) {
                person.setContractor(contractor);
            }
        });
    }

    @PreUpdate
    public void update(Contractor contractor) {
        if (contractor.getPersons() == null) return;
        contractor.getPersons().parallelStream().forEach((ContractPerson person) -> {
            if (person.getContractor() == null) {
                person.setContractor(contractor);
            }
        });
    }
}
