# Spring Boot - Multitenancy

This project aims at showing how to add multitenancy functionality to a Spring Boot application.

The multitenancy method used in the project is the one using one single database, but multiple schemas (one per tenant).
A master schema is used to keep track of the tenants who are allowed to use the application and to map them to the schema value.

Two tenants are defined:
* argus -> TENANT_ARGUS
* acme -> TENANT_ACME

## Prerequisites

Both the application and the tests rely on a PostgreSQL database up and running, and initialized with the provided schema and data.

1. Open a Terminal window, navigate to the `db` folder and run the command:

```bash
docker-compose up
```

The command will start a new PostgreSQL Docker container as defined in `db/docker-compose.yml`. 

2. Open a Terminal window, navigate to the `db/init` folder and run the command:

```bash
./db_init.sh
```

The command will create the database schemas defined in `db/init/schema.sql` and load the data defined in `db/init/data`.

## Testing the application

To test the application you can either run the auto tests or run the application and test the rest endpoints.

Remember to add a `X-TenantName` HTTP header with the value of the tenant you want to use (TENANT_ACME or TENANT_ARGUS).