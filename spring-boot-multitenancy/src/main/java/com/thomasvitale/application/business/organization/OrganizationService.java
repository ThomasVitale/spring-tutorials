package com.thomasvitale.application.business.organization;

import com.thomasvitale.application.data.organization.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationService {
    private final OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public List<OrganizationDTO> getAllOrganizations() {
        List<OrganizationDTO> organizationDTOs = new ArrayList<>();
        organizationRepository.findAll().forEach( organization ->
                organizationDTOs.add(new OrganizationDTO(organization.getName(), organization.getDescription()))
        );
        return organizationDTOs;
    }
}
