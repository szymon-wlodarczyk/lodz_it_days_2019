CREATE DATABASE ldi2019database OWNER ldi2019admin;

CREATE SCHEMA IF NOT EXISTS ldischema;

ALTER SCHEMA ldischema OWNER TO ldi2019admin;

CREATE SEQUENCE ldischema.events_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE ldischema.events_seq OWNER TO ldi2019admin;

CREATE TABLE ldischema.events (
    id bigint NOT NULL,
    event_type character varying(255),
    event_owner character varying(255),
    processor_id character varying(255),
    device_timestamp bigint,
    server_timestamp bigint,
    payload character varying(2000)
);

ALTER TABLE ldischema.events OWNER TO ldi2019admin;