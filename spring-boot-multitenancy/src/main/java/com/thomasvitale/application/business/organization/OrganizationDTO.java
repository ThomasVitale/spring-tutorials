package com.thomasvitale.application.business.organization;

public class OrganizationDTO {
    private String name;
    private String organization;

    public OrganizationDTO(String name, String organization) {
        this.name = name;
        this.organization = organization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
