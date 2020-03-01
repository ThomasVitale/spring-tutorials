package com.thomasvitale.application.tenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TenantService {
    private final TenantRepository tenantRepository;

    @Autowired
    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    public List<String> getTenantSchemas() {
        return StreamSupport.stream(tenantRepository.findAll().spliterator(), false)
                .map(TenantInfo::getTenantSchema)
                .collect(Collectors.toList());
    }
}
