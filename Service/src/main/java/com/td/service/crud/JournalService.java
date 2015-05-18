package com.td.service.crud;

import com.td.model.context.qualifier.JournalQualifier;
import com.td.model.entity.journal.JournalModel;
import com.td.model.repository.SectionRepository;
import com.td.service.context.qualifier.JournalSectionQualifier;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by zerotul.
 */
@Service
@JournalSectionQualifier
public class JournalService implements SectionService<JournalModel> {

    private final SectionRepository<JournalModel> journalRepository;

    @Inject
    public JournalService(@JournalQualifier SectionRepository<JournalModel> journalRepository) {
        this.journalRepository = journalRepository;
    }

    @Override
    public List<JournalModel> findAll() {
        return this.journalRepository.findAll();
    }
}
