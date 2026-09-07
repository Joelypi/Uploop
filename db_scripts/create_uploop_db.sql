
CREATE DATABASE IF NOT EXISTS uploop_db;
USE uploop_db;

-- Tabella utenti: usata da MySqlUserDAO (findByUsername, save)
CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255) NOT NULL
);

-- Tabella ordini: usata da MySqlOrderDAO (salvaOrdine, getOrdiniUtente)
CREATE TABLE IF NOT EXISTS orders (
    id_ordine INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    formato VARCHAR(50) NOT NULL,
    quantita INT NOT NULL,
    tipo_carta VARCHAR(50) NOT NULL,
    grafica_vettoriale BOOLEAN NOT NULL DEFAULT FALSE,
    note VARCHAR(255),
    FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE
);

-- un utente di prova per verificare subito il login
-- INSERT INTO users (username, password) VALUES ('mario', 'password123');
