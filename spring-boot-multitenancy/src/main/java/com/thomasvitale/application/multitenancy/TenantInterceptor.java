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

    @Value("${multitenant.header}")
    private String tenantHeader;

    @Value("${multitenant.default.name}")
    private String defaultTenantName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String tenantId = request.getHeader(this.tenantHeader);
        TenantContext.setCurrentTenant(Objects.requireNonNullElse(tenantId, defaultTenantName));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        TenantContext.clear();
    }
}
