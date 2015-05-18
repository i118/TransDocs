package com.td.model.entity;

/**
 * Created by zerotul on 30.03.15.
 */
public class AdminSection extends AbstractTransientModel {

    private static final long serialVersionUID = 4642598113255073499L;

    private String description;

    private String sectionType;

    public AdminSection() {
    }

    public AdminSection(String description, String sectionType) {
        this.description = description;
        this.sectionType = sectionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSectionType() {
        return sectionType;
    }

    public void setSectionType(String sectionType) {
        this.sectionType = sectionType;
    }
}
