
CREATE TABLE personal_info (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    title VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(255),
    location VARCHAR(255),
    summary TEXT,
    linkedin VARCHAR(255),
    github VARCHAR(255),
    website VARCHAR(255)
);

CREATE TABLE experience (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    company VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    description TEXT,
    start_date DATE,
    end_date DATE,
    location VARCHAR(255),
    order_index INT
);

CREATE TABLE language (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    level VARCHAR(255) NOT NULL
);

CREATE TABLE certification (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    issuer VARCHAR(255) NOT NULL,
    date DATE,
    url VARCHAR(255),
    description TEXT
);

CREATE TABLE programming_knowledge (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    level VARCHAR(255) NOT NULL
);

CREATE TABLE testing_knowledge (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    level VARCHAR(255) NOT NULL
);

INSERT INTO personal_info (name, title, email, phone, location, summary, linkedin, github)
VALUES ('Cristhian', 'Desarrollador Full-Stack', 'cristhian@email.com', '123456789', 'Lima, Perú',
        'Desarrollador con experiencia en Java, Spring Boot y tecnologías web.',
        'linkedin.com/in/cristhian', 'github.com/cristhian');

INSERT INTO experience (company, role, description, start_date, end_date, location, order_index)
VALUES ('Tech Corp', 'Full-Stack Developer', 'Desarrollo de aplicaciones web con Spring Boot y React.',
        '2022-01-01', '2024-06-01', 'Lima', 1);

INSERT INTO language (name, level) VALUES ('Español', 'Nativo'), ('Inglés', 'Intermedio');

INSERT INTO certification (name, issuer, date) VALUES ('AWS Certified', 'Amazon', '2024-06-01');

INSERT INTO programming_knowledge (name, level) VALUES ('Java', 'Avanzado'), ('Spring Boot', 'Avanzado');

INSERT INTO testing_knowledge (name, level) VALUES ('Selenium', 'Intermedio'), ('JUnit', 'Avanzado');