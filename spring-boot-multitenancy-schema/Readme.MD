# Spring Boot - Multitenancy

This project aims at showing how to add multitenancy functionality to a Spring Boot application.

The multitenancy method used in the project is the one using one single database, but multiple schemas (one per tenant).

Two tenants are defined:
* Argus -> TENANT_ARGUS
* Acme -> TENANT_ACME

The default schema is defined as a property in application.yml. In this demo the default tenant identifier is "MASTER".

## Prerequisites

Both the application and the tests rely on a PostgreSQL database up and running, and initialized with the provided schema and data.

1. Open a Terminal window, navigate to the `db` folder and run the command:

```bash
docker-compose up -d
```

The command will start a new PostgreSQL Docker container as defined in `db/docker-compose.yml`. 

2. Open a Terminal window, navigate to the `db/init` folder and run the command:

```bash
./db_init.sh
```

The command will create the database schemas defined in `db/init/schema.sql` and load the data defined in `db/init/data`.

If you get an error message due to the lack of permissions to execute the command above, then run this first:

```bash
chmod +x db_init.sh 
```

## Testing the application

To test the application you can either run the auto tests or run the application and test the rest endpoints.

Remember to add a `X-TenantId` HTTP header with the value of the tenant you want to use (TENANT_ACME or TENANT_ARGUS).