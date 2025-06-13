-- V2__Create_users_and_roles.sql
CREATE TABLE users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY(username) REFERENCES users(username)
);

CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);

-- Insert regular user
INSERT INTO users (username, password, enabled)
VALUES ('user', '{noop}userpass', true);

INSERT INTO authorities (username, authority)
VALUES ('user', 'ROLE_USER');

-- Insert admin user
INSERT INTO users (username, password, enabled)
VALUES ('admin', '{noop}adminpass', true);

INSERT INTO authorities (username, authority)
VALUES ('admin', 'ROLE_ADMIN');
