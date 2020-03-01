package com.thomasvitale.application.config;

import com.thomasvitale.application.multitenancy.MultitenancyProperties;
import com.thomasvitale.application.tenant.TenantService;
import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    @Bean
    public Flyway flyway(DataSource dataSource, MultitenancyProperties multitenancyProperties) {
        Flyway flyway = Flyway.configure()
                .locations("db/migration/master")
                .dataSource(dataSource)
                .schemas(multitenancyProperties.getDefaultTenantId())
                .load();
        flyway.migrate();
        return flyway;
    }

    @Bean
    CommandLineRunner commandLineRunner(DataSource dataSource, TenantService tenantService) {
        return args -> tenantService.getTenantSchemas().forEach(tenantSchema -> {
            Flyway flyway = Flyway.configure()
                    .locations("db/migration/tenants")
                    .dataSource(dataSource)
                    .schemas(tenantSchema)
                    .load();
            flyway.migrate();
        });
    }
}
