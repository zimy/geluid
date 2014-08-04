INSERT INTO users (username, password, enabled) VALUES ('root', '123456', TRUE);
INSERT INTO user_roles (username, role) VALUES (0, 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES (0, 'ROLE_ADMIN');
-- INSERT INTO users(username,password,enabled) VALUES ('root','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', TRUE);