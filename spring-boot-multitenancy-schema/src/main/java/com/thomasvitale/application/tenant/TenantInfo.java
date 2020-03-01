package com.thomasvitale.application.tenant;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TENANT_INFO")
public class TenantInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TENANT_NAME")
    @Size(max = 32)
    private String tenantName;

    @Column(name = "TENANT_SCHEMA")
    @Size(max = 32)
    private String tenantSchema;

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTenantSchema() {
        return tenantSchema;
    }

    public void setTenantSchema(String tenantSchema) {
        this.tenantSchema = tenantSchema;
    }
}
