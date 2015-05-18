package com.td.model.entity.journal;

import com.td.model.entity.AbstractTransientModel;

/**
 * Created by zerotul on 28.01.15.
 */
public class JournalModel extends AbstractTransientModel {

    private static final long serialVersionUID = 7146303971331273223L;

    private String description;

    private String journalType;

    public JournalModel() {
    }

    public JournalModel(String description, String journalType) {
        this.description = description;
        this.journalType = journalType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJournalType() {
        return journalType;
    }

    public void setJournalType(String journalType) {
        this.journalType = journalType;
    }
}
