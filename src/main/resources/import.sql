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


-- Insert addresses for clients 3 to 8
INSERT INTO address (id, street, street_number, city, postal_code, country) VALUES (3, 'High St', '23', 'Sky City', '23456', 'Skyland');
INSERT INTO address (id, street, street_number, city, postal_code, country) VALUES (4, 'Green Ave', '54', 'Nature Town', '34567', 'Greenland');
INSERT INTO address (id, street, street_number, city, postal_code, country) VALUES (5, 'Water Way', '78', 'Aqua City', '45678', 'Waterland');
INSERT INTO address (id, street, street_number, city, postal_code, country) VALUES (6, 'Health St', '90', 'Healthville', '56789', 'Healthland');
INSERT INTO address (id, street, street_number, city, postal_code, country) VALUES (7, 'Light Ave', '61', 'Bright City', '67890', 'Lightland');
INSERT INTO address (id, street, street_number, city, postal_code, country) VALUES (8, 'Ocean Dr', '72', 'Breeze City', '78901', 'Oceanland');

-- Update clients 3 to 8 with their corresponding address_id
INSERT INTO clients (id, company_name, legal_name, cuit_number, tax_condition, email, phone, address_id, enabled) VALUES (3, 'SkyHigh Innovations', 'SkyHigh Corp.', '20-33445566-8', 'MONOTRIBUTO', 'skyhigh@email.com', '+1 555-135792', 3, 1);
INSERT INTO clients (id, company_name, legal_name, cuit_number, tax_condition, email, phone, address_id, enabled) VALUES (4, 'NatureVibes Organics', 'NatureVibes Ltd.', '27-88776655-9', 'MONOTRIBUTO', 'naturevibes@email.com', '+1 555-246813', 4, 1);
INSERT INTO clients (id, company_name, legal_name, cuit_number, tax_condition, email, phone, address_id, enabled) VALUES (5, 'AquaPurity Water', 'AquaPurity Inc.', '23-11223344-0', 'EXENTO', 'aquapurity@email.com', '+1 555-102938', 5, 1);
INSERT INTO clients (id, company_name, legal_name, cuit_number, tax_condition, email, phone, address_id, enabled) VALUES (6, 'HealthGuard Pharmaceuticals', 'HealthGuard Pharma LLC', '34-22334455-1', 'EXENTO', 'healthguard@email.com', '+1 555-465789', 6, 1);
INSERT INTO clients (id, company_name, legal_name, cuit_number, tax_condition, email, phone, address_id, enabled) VALUES (7, 'BrightLight Solar', 'BrightLight Energy LLC', '31-55667788-2', 'RESPONSABLE_INSCRIPTO', 'brightlight@email.com', '+1 555-987123', 7, 1);

-- Workers Addresses

INSERT INTO address (id, street, street_number, city, postal_code, country) VALUES (9, 'Mountain Rd', '10', 'Peakville', '89012', 'Mountainland');
INSERT INTO address (id, street, street_number, city, postal_code, country) VALUES (10, 'Sunny Ln', '45', 'Sunshine City', '90123', 'Sunnyland');
INSERT INTO address (id, street, street_number, city, postal_code, country) VALUES (11, 'Garden Ave', '32', 'Blossom Town', '01234', 'Gardenland');
INSERT INTO address (id, street, street_number, city, postal_code, country) VALUES (12, 'Park Rd', '78', 'Green Park', '12345', 'Parkland');
INSERT INTO address (id, street, street_number, city, postal_code, country) VALUES (13, 'Main St', '15', 'Central City', '23456', 'Mainland');
INSERT INTO address (id, street, street_number, city, postal_code, country) VALUES (14, 'Sunset Blvd', '87', 'Twilight City', '34567', 'Sunsetland');
INSERT INTO address (id, street, street_number, city, postal_code, country) VALUES (15, 'River Rd', '92', 'Riverside', '45678', 'Riverland');
INSERT INTO address (id, street, street_number, city, postal_code, country) VALUES (16, 'Forest Dr', '27', 'Woodland', '56789', 'Forestland');


-- Workers
INSERT INTO workers (first_name, last_name, cuit_number, address_id, tax_condition, email, phone, birth_date) VALUES ('John', 'Doe', '1234567890', 9, 'RESPONSABLE_INSCRIPTO', 'john@example.com', '123456789', '1990-01-01');
INSERT INTO workers (first_name, last_name, cuit_number, address_id, tax_condition, email, phone, birth_date) VALUES ('Jane', 'Smith', '9876543210', 10, 'MONOTRIBUTO', 'jane@example.com', '987654321', '1995-02-02');
INSERT INTO workers (first_name, last_name, cuit_number, address_id, tax_condition, email, phone, birth_date) VALUES ('Mike', 'Johnson', '4567890123', 11, 'EXENTO', 'mike@example.com', '456789012', '1985-03-03');
INSERT INTO workers (first_name, last_name, cuit_number, address_id, tax_condition, email, phone, birth_date) VALUES ('Emily', 'Williams', '0123456789', 12, 'RESPONSABLE_INSCRIPTO', 'emily@example.com', '012345678', '1992-04-04');
INSERT INTO workers (first_name, last_name, cuit_number, address_id, tax_condition, email, phone, birth_date) VALUES ('David', 'Brown', '7890123456', 13, 'MONOTRIBUTO', 'david@example.com', '789012345', '1988-05-05');
INSERT INTO workers (first_name, last_name, cuit_number, address_id, tax_condition, email, phone, birth_date) VALUES ('Sarah', 'Taylor', '2345678901', 14, 'EXENTO', 'sarah@example.com', '234567890', '1994-06-06');
INSERT INTO workers (first_name, last_name, cuit_number, address_id, tax_condition, email, phone, birth_date) VALUES ('Michael', 'Anderson', '5678901234', 15, 'RESPONSABLE_INSCRIPTO', 'michael@example.com', '567890123', '1991-07-07');
INSERT INTO workers (first_name, last_name, cuit_number, address_id, tax_condition, email, phone, birth_date) VALUES ('Olivia', 'Martinez', '8901234567', 16, 'MONOTRIBUTO', 'olivia@example.com', '890123456', '1987-08-08');
