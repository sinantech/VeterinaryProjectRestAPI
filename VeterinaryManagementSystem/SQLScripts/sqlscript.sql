-- Switch to the employee_directory database (not needed in PostgreSQL)
-- USE `employee_directory`;

-- Drop tables if they exist
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;

-- Table structure for table `users`
CREATE TABLE users (
  username varchar(50) NOT NULL,
  password varchar(50) NOT NULL,
  enabled boolean NOT NULL,
  PRIMARY KEY (username)
);

-- Inserting data for table `users`
INSERT INTO users (username, password, enabled) VALUES
('lewis', '{noop}test123', TRUE),
('max', '{noop}test123', TRUE),
('charles', '{noop}test123', TRUE);

-- Table structure for table `authorities`
CREATE TABLE authorities (
  username varchar(50) NOT NULL,
  authority varchar(50) NOT NULL,
  UNIQUE (username, authority),
  FOREIGN KEY (username) REFERENCES users (username)
);

-- Inserting data for table `authorities`
INSERT INTO authorities (username, authority) VALUES
('lewis', 'ROLE_employee'),
('max', 'ROLE_employee'),
('max', 'ROLE_manager'),
('charles', 'ROLE_manager'),
('charles', 'ROLE_manager'),
('charles', 'ROLE_admin');
