package com.thomasvitale.application.data.tenant;

import javax.persistence.*;

@Entity
@Table(name = "TENANT_INFO")
public class TenantInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TENANT_NAME")
    private String tenantName;
    @Column(name = "TENANT_SCHEMA")
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
