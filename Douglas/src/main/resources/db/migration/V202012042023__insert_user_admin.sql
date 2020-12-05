INSERT INTO authorization(id, address, cell_phone, email, first_name, last_name, password)
VALUES(1, '', '', 'eliotandelon@gmail.com', 'ADMIN', 'ADMIN', '$2a$10$7Y3.GLWc499jDMEaA1AON.7JNaNTzGpw1yOwXbzFwGfltwuwGQleS');

INSERT INTO authorization_role(id, authorization_id, role_id)
VALUES(1, 1, 1);

INSERT INTO authorization_authorization_role(authorization_id, authorization_role_id)
VALUES(1, 1);