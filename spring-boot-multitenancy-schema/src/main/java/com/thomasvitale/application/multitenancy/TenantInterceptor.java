package com.thomasvitale.application.multitenancy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
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

    @Value("${multitenancy.http-header}")
    private String tenantHttpHeader;

    @Value("${multitenancy.default-tenant-id}")
    private String defaultTenantIdentifier;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String tenantId = request.getHeader(this.tenantHttpHeader);
        TenantContext.setCurrentTenant(Objects.requireNonNullElse(tenantId, defaultTenantIdentifier));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        TenantContext.clear();
    }
}
