-- Master data
INSERT INTO MASTER.TENANT_INFO (TENANT_NAME, TENANT_SCHEMA) values ('acme', 'TENANT_ACME');
INSERT INTO MASTER.TENANT_INFO (TENANT_NAME, TENANT_SCHEMA) values ('argus', 'TENANT_ARGUS');

-- Acme data
INSERT INTO TENANT_ACME.ORGANIZATION (NAME, DESCRIPTION) values ('Acme Department 1', 'Cartoon department');
INSERT INTO TENANT_ACME.ORGANIZATION (NAME, DESCRIPTION) values ('Acme Department 2', 'Another cartoon department');

-- Argus data
INSERT INTO TENANT_ARGUS.ORGANIZATION (NAME, DESCRIPTION) values ('Argus Division 1', 'Secret division');
INSERT INTO TENANT_ARGUS.ORGANIZATION (NAME, DESCRIPTION) values ('Argus Division 2', 'Another secret division');