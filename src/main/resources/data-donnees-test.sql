-- Insertion des données dans la table Utilisateur
INSERT INTO Utilisateur (email, password) VALUES
('admin@example.com', '$2a$10$8I2O3raub9W02hsaPdAaYug5kLIA0JrcfriulOcWd5.5zzUtJDhua'),
('entreprise1@example.com', '$2a$10$8I2O3raub9W02hsaPdAaYug5kLIA0JrcfriulOcWd5.5zzUtJDhua'),
('entreprise2@example.com', '$2a$10$8I2O3raub9W02hsaPdAaYug5kLIA0JrcfriulOcWd5.5zzUtJDhua');

-- Insertion des données dans la table Entreprise
INSERT INTO Entreprise (nom, utilisateur_id) VALUES
('Entreprise 1', 2),
('Entreprise 2', 3);

-- Insertion des données dans la table Convention
INSERT INTO Convention (nom, subvention, salarie_maximum, entreprise_id) VALUES
('Convention 1', 1000.0, 1, 1),
('Convention 2', 1500.0, 10, 1),
('Convention 3', 2000.0, 3, 2);

-- Insertion des données dans la table Salarie
INSERT INTO Salarie (matricule, code_barre, convention_id) VALUES
('SAL001', 'CODEBARRE001', 1),
('SAL002', 'CODEBARRE002', 2),
('SAL003', 'CODEBARRE003',2),
('SAL004', 'CODEBARRE004', 3);