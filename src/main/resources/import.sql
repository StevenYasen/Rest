INSERT INTO roles (id, name) VALUES (1, 'ROLE_USER'),(2,'ROLE_ADMIN');

INSERT INTO users (email, password, username, lastname) VALUES ('admin@mail.ru', '$2a$12$aZ0EneSIaS/io4XWvLFoS.16Kkv2DvvIEfw/Onu34CTsh.NvEPjb.', 'admin', 'adminov'),('user@gmail.com','$2a$12$YEjzSVAU9uVAvpMkC5zufuPxvm.RadMv7c5GM7OqU9.fPjwuOUH/q','user', 'userus');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 2),(1, 1), (2, 1);