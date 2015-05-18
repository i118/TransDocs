package com.td.service.crud;

import com.td.model.context.qualifier.AdminSectionQualifier;
import com.td.model.entity.AdminSection;
import com.td.model.repository.SectionRepository;
import com.td.service.context.qualifier.AdminServiceQualifier;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by zerotul.
 */
@Service
@AdminServiceQualifier
public class AdminSectionService implements SectionService<AdminSection> {

    private final SectionRepository<AdminSection> adminService;

    @Inject
    public AdminSectionService(@AdminSectionQualifier SectionRepository<AdminSection> adminService) {
        this.adminService = adminService;
    }

    @Override
    public List<AdminSection> findAll() {
        return this.adminService.findAll();
    }
}
