-- V1__Initial_setup.sql
-- Initial database setup for ConectaPr

CREATE SCHEMA IF NOT EXISTS flyway;

-- Example table, replace with actual schema
CREATE TABLE IF NOT EXISTS flyway.example (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
