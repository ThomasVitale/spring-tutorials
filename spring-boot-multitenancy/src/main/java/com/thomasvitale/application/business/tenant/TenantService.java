package com.thomasvitale.application.business.tenant;

import com.thomasvitale.application.data.tenant.TenantInfo;
import com.thomasvitale.application.data.tenant.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantService {
    private final TenantRepository tenantRepository;

    @Autowired
    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    public String getTenantSchema(String tenantName) {
        TenantInfo tenantInfo = tenantRepository.findTenantInfoByTenantName(tenantName);
        return tenantInfo.getTenantSchema();
    }
}
