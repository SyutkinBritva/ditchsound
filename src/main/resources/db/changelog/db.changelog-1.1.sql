-- liquibase formatted sql

-- changeset iOvcharenko:1
CREATE TYPE work_description AS ENUM (
    'MIXING',
    'MASTERING',
    'PRODUCING',
    'EDITING',
    'WRITING'
    );
-- changeset iOvcharenko:2
ALTER TABLE release ALTER COLUMN work_description TYPE
    work_description[] USING work_description::work_description[];