-- Создание таблицы пользователей
CREATE TABLE users (
                       id          BIGSERIAL,
                       username    VARCHAR(30) NOT NULL UNIQUE,
                       password    VARCHAR(80) NOT NULL,
                       email       VARCHAR(50) UNIQUE,
                       PRIMARY KEY (id)
);

-- Создание таблицы ролей
CREATE TABLE roles (
                       id          SERIAL,
                       name        VARCHAR(50) NOT NULL,
                       PRIMARY KEY (id)
);

-- Создание таблицы связи пользователей и ролей (многие-ко-многим)
CREATE TABLE users_roles (
                             user_id     BIGINT NOT NULL,
                             role_id     INT NOT NULL,
                             PRIMARY KEY (user_id, role_id),
                             FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
                             FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);

-- Добавление стандартных ролей
INSERT INTO roles (name) VALUES
                             ('ROLE_USER'),
                             ('ROLE_ADMIN');

-- Добавление тестовых пользователей (пароль: 'password' зашифрован BCrypt)
INSERT INTO users (username, password, email) VALUES
                                                  ('user', '$2a$04$Fx/SX9.BAvtPUWyIIqqFx.hLY2Xp8nnhpzvEEVIMvVpwIPbA3v/.i', 'user@gmail.com'),
                                                  ('admin', '$2a$04$Fx/SX9.BAvtPUWyIIqqFx.hLY2Xp8nnhpzvEEVIMvVpwIPbA3v/.i', 'admin@gmail.com');

-- Назначение ролей пользователям
INSERT INTO users_roles (user_id, role_id) VALUES
                                               (1, 1),  -- user имеет ROLE_USER
                                               (2, 2);  -- admin имеет ROLE_ADMIN

-- Индексы для ускорения поиска
CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_users_email ON users(email);