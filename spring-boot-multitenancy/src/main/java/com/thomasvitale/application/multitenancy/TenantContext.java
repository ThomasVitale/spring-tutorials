package com.thomasvitale.application.multitenancy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A shared context for the current tenant.
 * It is set before each request and removed after.
 */
public class TenantContext {
    private static Logger logger = LoggerFactory.getLogger(TenantContext.class.getName());
    private static ThreadLocal<String> currentTenant = new InheritableThreadLocal<>();

    private TenantContext() {}

    public static void setCurrentTenant(String tenant) {
        logger.info("Setting tenant to {}", tenant);
        currentTenant.set(tenant);
    }

    public static String getCurrentTenant() {
        return currentTenant.get();
    }

    public static void clear() {
        currentTenant.remove();
    }
}
