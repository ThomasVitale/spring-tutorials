package com.thomasvitale.application.multitenancy;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Component
@ConfigurationProperties(prefix = "multitenancy")
public class MultitenancyProperties {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+[a-zA-Z_-]*[a-zA-Z]+$")
    private String httpHeader;

    @NotBlank
    @Pattern(regexp = "^[A-Z]+[A-Z_]*[A-Z]+$")
    private String defaultTenantId;

    public String getHttpHeader() {
        return httpHeader;
    }

    public void setHttpHeader(String httpHeader) {
        this.httpHeader = httpHeader;
    }

    public String getDefaultTenantId() {
        return defaultTenantId;
    }

    public void setDefaultTenantId(String defaultTenantId) {
        this.defaultTenantId = defaultTenantId;
    }
}
