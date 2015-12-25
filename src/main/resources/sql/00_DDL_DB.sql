-- Create the tablespace
create tablespace DB_BARCH
datafile '/opt/oracle/app/oracle/oradata/orcl/DB_SSMSSM.dbf' size 200M
autoextend ON next 100M maxsize unlimited logging
extent management local autoallocate
segment space management auto;

-- Create the user
create user ssmssm
  identified by ssmssm
  default tablespace DB_SSMSSM
  temporary tablespace TEMP
  profile DEFAULT;
-- Grant/Revoke role privileges
grant dba to ssmssm with admin option;
grant connect to ssmssm with admin option;


--postgresql uuid
/opt/PostgreSQL/9.4/bin/psql -d postgres -U postgres -f /opt/PostgreSQL/9.4/share/postgresql/extension/uuid-ossp--1.0.sql
