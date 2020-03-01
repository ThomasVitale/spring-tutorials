package com.thomasvitale.application.multitenancy;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Provides tenant-aware connection to the data source,
 * using the schema of the relevant tenant.
 */
@Component
public class MultiTenantConnectionProviderImpl implements MultiTenantConnectionProvider {
    private final transient DataSource dataSource;
    private final transient MultitenancyProperties multitenancyProperties;

    @Autowired
    public MultiTenantConnectionProviderImpl(DataSource dataSource, MultitenancyProperties multitenancyProperties) {
        this.dataSource = dataSource;
        this.multitenancyProperties = multitenancyProperties;
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        final Connection connection = getAnyConnection();
        try (Statement statement = connection.createStatement()) {
            // SQL statement specific for PostgreSQL
            statement.execute(String.format("SET search_path to %s;", tenantIdentifier));
        } catch (SQLException ex) {
            throw new HibernateException("Could not set the schema for the specified tenant: " + tenantIdentifier, ex);
        }

        return connection;
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            // SQL statement specific for PostgreSQL
            statement.execute(String.format("SET search_path to %s;", multitenancyProperties.getDefaultTenantId()));
        } catch (SQLException ex) {
            // Throw an exception to make sure the connection is not returned to the pool,
            // when not possible to reset the tenant schema usage.
            throw new HibernateException("Could not release the schema for the specified tenant: " + tenantIdentifier, ex);
        }

        connection.close();
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return true;
    }

    @Override
    public boolean isUnwrappableAs(Class unwrapType) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        return null;
    }
}
