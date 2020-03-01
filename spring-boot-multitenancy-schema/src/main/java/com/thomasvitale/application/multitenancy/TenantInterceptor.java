package com.thomasvitale.application.multitenancy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * An interceptor that reads the tenant header for each HTTP request
 * and sets the tenant context.
 * After the request has been handled, it clears the tenant context.
 */
@Component
public class TenantInterceptor extends HandlerInterceptorAdapter {

    private final MultitenancyProperties multitenancyProperties;

    @Autowired
    public TenantInterceptor(MultitenancyProperties multitenancyProperties) {
        this.multitenancyProperties = multitenancyProperties;
    }

    /**
     * This method is called before a synchronous or asynchronous request is handled.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String tenantId = request.getHeader(multitenancyProperties.getHttpHeader());
        TenantContext.setCurrentTenant(Objects.requireNonNullElse(tenantId, multitenancyProperties.getDefaultTenantId()));
        return true;
    }

    /**
     * This method is called after a synchronous request has been completed, either with success or failure.
     * Used instead of {@code postHandle}, which is only executed upon success, causing the tenant context
     * to not be cleared properly.
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        TenantContext.clear();
    }

    /**
     * This method is called instead of {@code postHandle} and {@code afterCompletion}
     * when the handler is being executed concurrently.
     */
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        TenantContext.clear();
    }
}
