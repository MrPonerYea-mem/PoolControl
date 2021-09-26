/*DROP TABLE IF EXISTS billionaires;

CREATE TABLE billionaires (
                              id INT AUTO_INCREMENT  PRIMARY KEY,
                              first_name VARCHAR(250) NOT NULL,
                              last_name VARCHAR(250) NOT NULL,
                              career VARCHAR(250) DEFAULT NULL
);

INSERT INTO billionaires (first_name, last_name, career) VALUES
('Aliko', 'Dangote', 'Billionaire Industrialist'),
('Bill', 'Gates', 'Billionaire Tech Entrepreneur'),
('Folrunsho', 'Alakija', 'Billionaire Oil Magnate');*/


/*
DROP TABLE IF EXISTS GROUPS;

CREATE TABLE GROUPS (

);
*/
-- INSTRUCTORS
INSERT INTO users (id, username, password, email, gender, role, time_to_work, prefer_time_start, prefer_time_end,
                   created_at, updated_at)
VALUES (1, 'instructor1', 'instructor1', 'user4@mail.com', 'MALE', 'INSTRUCTOR', 40, 32400, 43200,
        '2021-08-22 15:58:49.713', '2021-08-22 15:58:49.713');

INSERT INTO users (id, username, password, email, gender, role, time_to_work, prefer_time_start, prefer_time_end,
                   created_at, updated_at)
VALUES (2, 'instructor2', 'instructor2', 'user4@mail.com', 'MALE', 'INSTRUCTOR', 1, 43200, 50400,
        '2021-08-22 15:58:49.713', '2021-08-22 15:58:49.713');

INSERT INTO users (id, username, password, email, gender, role, time_to_work, prefer_time_start, prefer_time_end,
                   created_at, updated_at)
VALUES (3, 'instructor3', 'instructor3', 'user4@mail.com', 'MALE', 'INSTRUCTOR', 10, 32400, 43200,
        '2021-08-22 15:58:49.713', '2021-08-22 15:58:49.713');

-- USERS
INSERT INTO users (id, username, password, email, gender, role, created_at, updated_at)
VALUES (4, 'user4', 'user4', 'user4@mail.com', 'MALE', 'USER', '2021-08-22 15:58:49.713', '2021-08-22 15:58:49.713');

INSERT INTO users (id, username, password, email, gender, role, created_at, updated_at)
VALUES (5, 'user5', 'user5', 'user4@mail.com', 'MALE', 'USER', '2021-08-22 15:58:49.713', '2021-08-22 15:58:49.713');

INSERT INTO users (id, username, password, email, gender, role, created_at, updated_at)
VALUES (6, 'user6', 'user6', 'user4@mail.com', 'FEMALE', 'USER', '2021-08-22 15:58:49.713', '2021-08-22 15:58:49.713');

-- ADMIN
INSERT INTO users (id, username, password, email, gender, role, created_at, updated_at)
VALUES (7, 'a', 'a', 'user4@mail.com', 'FEMALE', 'ADMIN', '2021-08-22 15:58:49.713', '2021-08-22 15:58:49.713');

-- GROUPS
INSERT INTO groups (id, time_start, cloakroom_m, cloakroom_w, user_id, date, created_at, updated_at)
VALUES (1, 43200, 1, 2, 1, '2021-09-19 15:58:49.713', '2021-09-19 15:58:49.713', '2021-09-19 15:58:49.713');

INSERT INTO groups (id, time_start, cloakroom_m, cloakroom_w, user_id, date, created_at, updated_at)
VALUES (2, 43200, 10, 20, 2, '2021-09-19 15:58:49.713', '2021-09-19 15:58:49.713', '2021-09-19 15:58:49.713');

INSERT INTO groups (id, time_start, cloakroom_m, cloakroom_w, user_id, date, created_at, updated_at)
VALUES (3, 43200, 1, 2, 3, '2021-09-19 15:58:49.713', '2021-09-19 15:58:49.713', '2021-09-19 15:58:49.713');

INSERT INTO groups (id, time_start, cloakroom_m, cloakroom_w, user_id, date, created_at, updated_at)
VALUES (4, 43200, 1, 0, 3, '2021-09-19 15:58:49.713', '2021-09-19 15:58:49.713', '2021-09-19 15:58:49.713');

--GROUPS-USER
INSERT INTO users_groups (id, user_id, group_id, created_at, updated_at)
VALUES (1, 4, 1, '2021-09-19 15:58:49.713', '2021-09-19 15:58:49.713');

INSERT INTO users_groups (id, user_id, group_id, created_at, updated_at)
VALUES (2, 4, 2, '2021-09-19 15:58:49.713', '2021-09-19 15:58:49.713');

INSERT INTO users_groups (id, user_id, group_id, created_at, updated_at)
VALUES (3, 5, 1, '2021-09-19 15:58:49.713', '2021-09-19 15:58:49.713');

INSERT INTO users_groups (id, user_id, group_id, created_at, updated_at)
VALUES (4, 6, 2, '2021-09-19 15:58:49.713', '2021-09-19 15:58:49.713');

INSERT INTO users_groups (id, user_id, group_id, created_at, updated_at)
VALUES (5, 6, 3, '2021-09-19 15:58:49.713', '2021-09-19 15:58:49.713');

INSERT INTO users_groups (id, user_id, group_id, created_at, updated_at)
VALUES (6, 7, 3, '2021-09-19 15:58:49.713', '2021-09-19 15:58:49.713');

INSERT INTO users_groups (id, user_id, group_id, created_at, updated_at)
VALUES (7, 4, 4, '2021-09-19 15:58:49.713', '2021-09-19 15:58:49.713');