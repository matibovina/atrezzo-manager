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

-- Contacts

-- Contactos para SkyHigh Innovations
INSERT INTO contacts (client_id, first_name, last_name, phone, email, department, position, profile_picture) VALUES (3, 'John', 'Doe', '+1 555-111111', 'john.doe@email.com', 'Ventas', 'Representante', '');

INSERT INTO contacts (client_id, first_name, last_name, phone, email, department, position, profile_picture) VALUES (3, 'Jane', 'Smith', '+1 555-222222', 'jane.smith@email.com', 'Soporte', 'Técnico', '');

INSERT INTO contacts (client_id, first_name, last_name, phone, email, department, position, profile_picture) VALUES (3, 'Michael', 'Johnson', '+1 555-333333', 'michael.johnson@email.com', 'Administración', 'Contador', '');

-- Contactos para NatureVibes Organics
INSERT INTO contacts (client_id, first_name, last_name, phone, email, department, position, profile_picture) VALUES (4, 'Sarah', 'Williams', '+1 555-444444', 'sarah.williams@email.com', 'Marketing', 'Especialista', '');

INSERT INTO contacts (client_id, first_name, last_name, phone, email, department, position, profile_picture) VALUES (4, 'David', 'Brown', '+1 555-555555', 'david.brown@email.com', 'Ventas', 'Gerente', '');

INSERT INTO contacts (client_id, first_name, last_name, phone, email, department, position, profile_picture) VALUES (4, 'Emily', 'Davis', '+1 555-666666', 'emily.davis@email.com', 'Soporte', 'Técnico', '');

-- Contactos para AquaPurity Water
INSERT INTO contacts (client_id, first_name, last_name, phone, email, department, position, profile_picture) VALUES (5, 'Daniel', 'Taylor', '+1 555-777777', 'daniel.taylor@email.com', 'Ventas', 'Representante', '');

INSERT INTO contacts (client_id, first_name, last_name, phone, email, department, position, profile_picture) VALUES (5, 'Olivia', 'Martin', '+1 555-888888', 'olivia.martin@email.com', 'Marketing', 'Especialista', '');

INSERT INTO contacts (client_id, first_name, last_name, phone, email, department, position, profile_picture) VALUES (5, 'Sophia', 'Moore', '+1 555-999999', 'sophia.moore@email.com', 'Administración', 'Contador', '');

-- Contactos para HealthGuard Pharmaceuticals
INSERT INTO contacts (client_id, first_name, last_name, phone, email, department, position, profile_picture) VALUES (6, 'Benjamin', 'Anderson', '+1 555-000000', 'benjamin.anderson@email.com', 'Soporte', 'Técnico', '');

INSERT INTO contacts (client_id, first_name, last_name, phone, email, department, position, profile_picture) VALUES (6, 'Mia', 'Clark', '+1 555-112233', 'mia.clark@email.com', 'Ventas', 'Representante', '');

INSERT INTO contacts (client_id, first_name, last_name, phone, email, department, position, profile_picture) VALUES (6, 'Jacob', 'Lewis', '+1 555-445566','jacob.lewis@email.com', 'Administración', 'Contador', '');

-- Contactos para BrightLight Solar
INSERT INTO contacts (client_id, first_name, last_name, phone, email, department, position, profile_picture) VALUES (7, 'Emma', 'Walker', '+1 555-677788', 'emma.walker@email.com', 'Marketing', 'Especialista', '');

INSERT INTO contacts (client_id, first_name, last_name, phone, email, department, position, profile_picture) VALUES (7, 'Liam', 'Harris', '+1 555-899900', 'liam.harris@email.com', 'Ventas', 'Representante', '');

INSERT INTO contacts (client_id, first_name, last_name, phone, email, department, position, profile_picture) VALUES (7, 'Ava', 'King', '+1 555-123456', 'ava.king@email.com', 'Soporte', 'Técnico', '');

-- Quotes para John Doe
INSERT INTO quotes (title, client_id, location, description, total_price, tax_percentage, tax_amount, total_with_tax, status) VALUES ('Quote1 for John Doe', 3, 'Los Angeles', 'Description for Quote1', 1000.00, 10, 100.00, 1100.00, 'REQUESTED');
INSERT INTO quotes (title, client_id, location, description, total_price, tax_percentage, tax_amount, total_with_tax, status) VALUES ('Quote2 for John Doe', 3, 'San Francisco', 'Description for Quote2', 2000.00, 10, 200.00, 2200.00, 'SENT');

-- Quotes para Jane Smith
INSERT INTO quotes (title, client_id, location, description, total_price, tax_percentage, tax_amount, total_with_tax, status) VALUES ('Quote1 for Jane Smith', 3, 'San Diego', 'Description for Quote1', 3000.00, 10, 300.00, 3300.00, 'ACCEPTED');
INSERT INTO quotes (title, client_id, location, description, total_price, tax_percentage, tax_amount, total_with_tax, status) VALUES ('Quote2 for Jane Smith', 3, 'San Jose', 'Description for Quote2', 4000.00, 10, 400.00, 4400.00, 'REJECTED');

-- Quotes para Michael Johnson
INSERT INTO quotes (title, client_id, location, description, total_price, tax_percentage, tax_amount, total_with_tax, status) VALUES ('Quote1 for Michael Johnson', 3, 'Sacramento', 'Description for Quote1', 5000.00, 10, 500.00, 5500.00, 'REQUESTED');
INSERT INTO quotes (title, client_id, location, description, total_price, tax_percentage, tax_amount, total_with_tax, status) VALUES ('Quote2 for Michael Johnson', 3, 'Palo Alto', 'Description for Quote2', 6000.00, 10, 600.00, 6600.00, 'SENT');

-- Quotes para Sarah Williams
INSERT INTO quotes (title, client_id, location, description, total_price, tax_percentage, tax_amount, total_with_tax, status) VALUES ('Quote1 for Sarah Williams', 4, 'Berkeley', 'Description for Quote1', 7000.00, 10, 700.00, 7700.00, 'ACCEPTED');
INSERT INTO quotes (title, client_id, location, description, total_price, tax_percentage, tax_amount, total_with_tax, status) VALUES ('Quote2 for Sarah Williams', 4, 'Santa Monica', 'Description for Quote2', 8000.00, 10, 800.00, 8800.00, 'REJECTED');

-- Servicios de Fotografía
INSERT INTO services (title, description, category, type) VALUES ('Fotografía Institucional', 'Fotografía profesional para instituciones', 'INSTITUCIONAL', 'PHOTOGRAPHY');
INSERT INTO services (title, description, category, type) VALUES ('Fotografía Empresarial', 'Fotografía profesional para empresas', 'EMPRESARIAL', 'PHOTOGRAPHY');
INSERT INTO services (title, description, category, type) VALUES ('Fotografía Publicitaria', 'Fotografía profesional para publicidad', 'PUBLICITARIO', 'PHOTOGRAPHY');
INSERT INTO services (title, description, category, type) VALUES ('Fotografía Social', 'Fotografía profesional para eventos sociales', 'SOCIAL', 'PHOTOGRAPHY');
INSERT INTO services (title, description, category, type) VALUES ('Edición de Fotografía', 'Servicio de edición profesional para fotografías', 'INSTITUCIONAL', 'PHOTOGRAPHY');
INSERT INTO services (title, description, category, type) VALUES ('Edición de Fotografía', 'Servicio de edición profesional para fotografías', 'EMPRESARIAL', 'PHOTOGRAPHY');
INSERT INTO services (title, description, category, type) VALUES ('Edición de Fotografía', 'Servicio de edición profesional para fotografías', 'PUBLICITARIO', 'PHOTOGRAPHY');
INSERT INTO services (title, description, category, type) VALUES ('Edición de Fotografía', 'Servicio de edición profesional para fotografías', 'SOCIAL', 'PHOTOGRAPHY');

-- Servicios de Video
INSERT INTO services (title, description, category, type) VALUES ('Video Institucional', 'Producción de videos para instituciones', 'INSTITUCIONAL', 'VIDEO');
INSERT INTO services (title, description, category, type) VALUES ('Video Empresarial', 'Producción de videos para empresas', 'EMPRESARIAL', 'VIDEO');
INSERT INTO services (title, description, category, type) VALUES ('Video Publicitario', 'Producción de videos para publicidad', 'PUBLICITARIO', 'VIDEO');
INSERT INTO services (title, description, category, type) VALUES ('Video Social', 'Producción de videos para eventos sociales', 'SOCIAL', 'VIDEO');
INSERT INTO services (title, description, category, type) VALUES ('Edición de Video', 'Servicio de edición profesional para videos', 'INSTITUCIONAL', 'VIDEO');
INSERT INTO services (title, description, category, type) VALUES ('Edición de Video', 'Servicio de edición profesional para videos', 'EMPRESARIAL', 'VIDEO');
INSERT INTO services (title, description, category, type) VALUES ('Edición de Video', 'Servicio de edición profesional para videos', 'PUBLICITARIO', 'VIDEO');
INSERT INTO services (title, description, category, type) VALUES ('Edición de Video', 'Servicio de edición profesional para videos', 'SOCIAL', 'VIDEO');

-- Asignando servicios de fotografía a los fotógrafos
INSERT INTO worker_services (worker_id, service_id) VALUES (1, 1);
INSERT INTO worker_services (worker_id, service_id) VALUES (1, 2);
INSERT INTO worker_services (worker_id, service_id) VALUES (1, 3);
INSERT INTO worker_services (worker_id, service_id) VALUES (1, 4);
INSERT INTO worker_services (worker_id, service_id) VALUES (2, 1);
INSERT INTO worker_services (worker_id, service_id) VALUES (2, 2);
INSERT INTO worker_services (worker_id, service_id) VALUES (2, 3);
INSERT INTO worker_services (worker_id, service_id) VALUES (2, 4);
INSERT INTO worker_services (worker_id, service_id) VALUES (3, 1);
INSERT INTO worker_services (worker_id, service_id) VALUES (3, 2);
INSERT INTO worker_services (worker_id, service_id) VALUES (3, 3);
INSERT INTO worker_services (worker_id, service_id) VALUES (3, 4);
INSERT INTO worker_services (worker_id, service_id) VALUES (4, 1);
INSERT INTO worker_services (worker_id, service_id) VALUES (4, 2);
INSERT INTO worker_services (worker_id, service_id) VALUES (4, 3);
INSERT INTO worker_services (worker_id, service_id) VALUES (4, 4);


-- Asignando servicios de video a los videógrafos
INSERT INTO worker_services (worker_id, service_id) VALUES (5, 9);
INSERT INTO worker_services (worker_id, service_id) VALUES (5, 10);
INSERT INTO worker_services (worker_id, service_id) VALUES (5, 11);
INSERT INTO worker_services (worker_id, service_id) VALUES (5, 12);
INSERT INTO worker_services (worker_id, service_id) VALUES (6, 9);
INSERT INTO worker_services (worker_id, service_id) VALUES (6, 10);
INSERT INTO worker_services (worker_id, service_id) VALUES (6, 11);
INSERT INTO worker_services (worker_id, service_id) VALUES (6, 12);
INSERT INTO worker_services (worker_id, service_id) VALUES (7, 9);
INSERT INTO worker_services (worker_id, service_id) VALUES (7, 10);
INSERT INTO worker_services (worker_id, service_id) VALUES (7, 11);
INSERT INTO worker_services (worker_id, service_id) VALUES (7, 12);
INSERT INTO worker_services (worker_id, service_id) VALUES (8, 9);
INSERT INTO worker_services (worker_id, service_id) VALUES (8, 10);
INSERT INTO worker_services (worker_id, service_id) VALUES (8, 11);
INSERT INTO worker_services (worker_id, service_id) VALUES (8, 12);