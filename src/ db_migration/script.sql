 --Create the base Composant table
CREATE TABLE Composant (
                           id SERIAL PRIMARY KEY,
                           nom VARCHAR(255) NOT NULL,
                           type_composant VARCHAR(50) NOT NULL,
                           taux_tva DECIMAL(5,2) NOT NULL
);

-- Create the Materiau table inheriting from Composant
CREATE TABLE Materiau (
                          cout_unitaire DECIMAL(10,2) NOT NULL,
                          quantite DECIMAL(10,2) NOT NULL,
                          cout_transport DECIMAL(10,2) NOT NULL,
                          coefficient_qualite DECIMAL(3,2) NOT NULL
) INHERITS (Composant);

-- Create the MainOeuvre table inheriting from Composant
CREATE TABLE MainOeuvre (
                            taux_horaire DECIMAL(10,2) NOT NULL,
                            heures_travail DECIMAL(10,2) NOT NULL,
                            productivite_ouvrier DECIMAL(3,2) NOT NULL
) INHERITS (Composant);

-- Create the Client table
CREATE TABLE Client (
                        id SERIAL PRIMARY KEY,
                        nom VARCHAR(255) NOT NULL,
                        adresse TEXT NOT NULL,
                        telephone VARCHAR(20) NOT NULL,
                        est_professionnel BOOLEAN NOT NULL
);

-- Create the Projet table
CREATE TABLE Projet (
                        id SERIAL PRIMARY KEY,
                        nom_projet VARCHAR(255) NOT NULL,
                        marge_beneficiaire DECIMAL(5,2) NOT NULL,
                        cout_total DECIMAL(12,2) NOT NULL,
                        etat_projet VARCHAR(20) NOT NULL,
                        client_id INTEGER REFERENCES Client(id)
);

-- Create the Devis table
CREATE TABLE Devis (
                       id SERIAL PRIMARY KEY,
                       montant_estime DECIMAL(12,2) NOT NULL,
                       date_emission DATE NOT NULL,
                       date_validite DATE NOT NULL,
                       accepte BOOLEAN NOT NULL,
                       projet_id INTEGER REFERENCES Projet(id)
);