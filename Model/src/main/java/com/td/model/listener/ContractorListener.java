package com.td.model.listener;

import com.td.model.entity.dictionary.company.Contractor;
import com.td.model.entity.dictionary.company.IContractPerson;
import com.td.model.entity.dictionary.company.JuridicalPerson;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Created by konstantinchipunov on 01.09.14.
 */
public class ContractorListener {
    @PrePersist
    public void persist(Contractor contractor) {
        if (contractor.getPersons() == null) return;
        contractor.getPersons().parallelStream().forEach((IContractPerson person) -> {
            if (person.getContractor() == null) {
                person.setContractor(contractor);
            }
        });
    }

    @PreUpdate
    public void update(Contractor contractor) {
        if (contractor.getPersons() == null) return;
        contractor.getPersons().parallelStream().forEach((IContractPerson person) -> {
            if (person.getContractor() == null) {
                person.setContractor(contractor);
            }
        });
    }
}
