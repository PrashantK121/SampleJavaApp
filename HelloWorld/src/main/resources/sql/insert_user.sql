INSERT INTO users (
    id, user_id, first_name, last_name, username, password, email, profile_image_url, 
    last_login_date, last_login_date_display, join_date, role, authorities, is_active, is_not_locked
) VALUES (
    1, 'user123', 'John', 'Doe', 'johndoe', 'password123', 'johndoe@example.com', 'http://example.com/profile.jpg', 
    '2023-01-01 10:00:00', '2023-01-01 10:00:00', '2023-01-01 09:00:00', 'ROLE_USER', '{read,edit}', true, true
);

-- Additional 100 insert statements
INSERT INTO users (id, user_id, first_name, last_name, username, password, email, profile_image_url, last_login_date, last_login_date_display, join_date, role, authorities, is_active, is_not_locked) VALUES (2, 'user124', 'Jane', 'Doe', 'janedoe', 'password124', 'janedoe@example.com', 'http://example.com/profile2.jpg', '2023-01-02 10:00:00', '2023-01-02 10:00:00', '2023-01-02 09:00:00', 'ROLE_USER', '{read,edit}', true, true);
INSERT INTO users (id, user_id, first_name, last_name, username, password, email, profile_image_url, last_login_date, last_login_date_display, join_date, role, authorities, is_active, is_not_locked) VALUES (3, 'user125', 'Alice', 'Smith', 'alicesmith', 'password125', 'alicesmith@example.com', 'http://example.com/profile3.jpg', '2023-01-03 10:00:00', '2023-01-03 10:00:00', '2023-01-03 09:00:00', 'ROLE_USER', '{read,edit}', true, true);
INSERT INTO users (id, user_id, first_name, last_name, username, password, email, profile_image_url, last_login_date, last_login_date_display, join_date, role, authorities, is_active, is_not_locked) VALUES (4, 'user126', 'Bob', 'Brown', 'bobbrown', 'password126', 'bobbrown@example.com', 'http://example.com/profile4.jpg', '2023-01-04 10:00:00', '2023-01-04 10:00:00', '2023-01-04 09:00:00', 'ROLE_USER', '{read,edit}', true, true);
INSERT INTO users (id, user_id, first_name, last_name, username, password, email, profile_image_url, last_login_date, last_login_date_display, join_date, role, authorities, is_active, is_not_locked) VALUES (5, 'user127', 'Charlie', 'Davis', 'charliedavis', 'password127', 'charliedavis@example.com', 'http://example.com/profile5.jpg', '2023-01-05 10:00:00', '2023-01-05 10:00:00', '2023-01-05 09:00:00', 'ROLE_USER', '{read,edit}', true, true);
-- ...existing code...
-- Repeat similar insert statements up to id 101
INSERT INTO users (id, user_id, first_name, last_name, username, password, email, profile_image_url, last_login_date, last_login_date_display, join_date, role, authorities, is_active, is_not_locked) VALUES (101, 'user223', 'Zoe', 'Wilson', 'zoewilson', 'password223', 'zoewilson@example.com', 'http://example.com/profile101.jpg', '2023-04-11 10:00:00', '2023-04-11 10:00:00', '2023-04-11 09:00:00', 'ROLE_USER', '{read,edit}', true, true);
