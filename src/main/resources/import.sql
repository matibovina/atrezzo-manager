INSERT INTO roles (id, role_name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (id, role_name) VALUES (2, 'ROLE_CLIENT');
INSERT INTO roles (id, role_name) VALUES (3, 'ROLE_WORKER');

INSERT INTO users (id, username, email, password, enabled, creation_date) VALUES (1, 'alice', 'alice.smith@email.com', 'password1', 1, '2023-05-02 10:00:00');
INSERT INTO users (id, username, email, password, enabled, creation_date) VALUES (2, 'bob', 'bob.johnson@email.com', 'password2', 1, '2023-05-03 11:00:00');
INSERT INTO users (id, username, email, password, enabled, creation_date) VALUES (3, 'carol', 'carol.williams@email.com', 'password3', 1, '2023-05-04 12:00:00');
INSERT INTO users (id, username, email, password, enabled, creation_date) VALUES (4, 'dave', 'dave.brown@email.com', 'password4', 1, '2023-05-05 13:00:00');
INSERT INTO users (id, username, email, password, enabled, creation_date) VALUES (5, 'erin', 'erin.jones@email.com', 'password5', 1, '2023-05-06 14:00:00');
INSERT INTO users (id, username, email, password, enabled, creation_date) VALUES (6, 'frank', 'frank.davis@email.com', 'password6', 1, '2023-05-07 15:00:00');
INSERT INTO users (id, username, email, password, enabled, creation_date) VALUES (7, 'grace', 'grace.garcia@email.com', 'password7', 1, '2023-05-08 16:00:00');
INSERT INTO users (id, username, email, password, enabled, creation_date) VALUES (8, 'hank', 'hank.miller@email.com', 'password8', 1, '2023-05-09 17:00:00');
INSERT INTO users (id, username, email, password, enabled, creation_date) VALUES (9, 'isabel', 'isabel.jackson@email.com', 'password9', 1, '2023-05-10 18:00:00');
INSERT INTO users (id, username, email, password, enabled, creation_date) VALUES (10, 'jim', 'jim.white@email.com', 'password10', 1, '2023-05-11 19:00:00');

INSERT INTO user_role (id, user_id, role_id) VALUES (1, 1, 1);
INSERT INTO user_role (id, user_id, role_id) VALUES (2, 2, 2);
INSERT INTO user_role (id, user_id, role_id) VALUES (3, 3, 2);
INSERT INTO user_role (id, user_id, role_id) VALUES (4, 4, 3);
INSERT INTO user_role (id, user_id, role_id) VALUES (5, 5, 3);
INSERT INTO user_role (id, user_id, role_id) VALUES (6, 6, 2);
INSERT INTO user_role (id, user_id, role_id) VALUES (7, 7, 1);
INSERT INTO user_role (id, user_id, role_id) VALUES (8, 8, 1);
INSERT INTO user_role (id, user_id, role_id) VALUES (9, 9, 2);
INSERT INTO user_role (id, user_id, role_id) VALUES (10, 10, 3);
