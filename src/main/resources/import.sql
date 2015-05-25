SET foreign_key_checks = 0;
TRUNCATE TABLE Stagiaire;
TRUNCATE TABLE Formation;
TRUNCATE TABLE Formateur;
TRUNCATE TABLE Session;
TRUNCATE TABLE Evaluation;
TRUNCATE TABLE Session_Stagiaire;
TRUNCATE TABLE Formateur_Formation;

TRUNCATE TABLE Utilisateur;
TRUNCATE TABLE Role;
TRUNCATE TABLE Utilisateur_Role;

CREATE TABLE Utilisateur (id INT NOT NULL AUTO_INCREMENT,login VARCHAR(30) NOT NULL, password VARCHAR(30) NOT NULL, PRIMARY KEY (id));
CREATE TABLE Role (id INT NOT NULL AUTO_INCREMENT,description VARCHAR(30) NOT NULL, PRIMARY KEY (id));
CREATE TABLE Utilisateur_Role (Utilisateur_Id INTEGER, Role_Id INTEGER);

INSERT INTO Utilisateur(id, login, password) values(1,'admin','admin');
INSERT INTO Role (id,description) value(1,'ROLE_USER');
INSERT INTO Role (id,description) value(2,'ROLE_ADMIN');
INSERT INTO Utilisateur_Role(Utilisateur_id, Role_id) values(1,1);
INSERT INTO Utilisateur_Role(Utilisateur_id, Role_id) values(1,2);


INSERT INTO Stagiaire (id, dateDeNaissance, email, nom, prenom) VALUES (NULL, '1977-02-04', 'stagiaire1@formation.com', 'Durant', 'Fabien');
INSERT INTO Stagiaire (id, dateDeNaissance, email, nom, prenom) VALUES (NULL, '1974-07-24', 'stagiaire2@formation.com', 'Dupont', 'Loïc');
INSERT INTO Stagiaire (id, dateDeNaissance, email, nom, prenom) VALUES (NULL, '1976-08-22', 'stagiaire3@formation.com', 'Treux', 'Julien');
INSERT INTO Stagiaire (id, dateDeNaissance, email, nom, prenom) VALUES (NULL, '1980-12-14', 'stagiaire4@formation.com', 'Bricot', 'Claire');
INSERT INTO Stagiaire (id, dateDeNaissance, email, nom, prenom) VALUES (NULL, '1970-01-04', 'stagiaire5@formation.com', 'Legrand', 'Lucien');
INSERT INTO Stagiaire (id, dateDeNaissance, email, nom, prenom) VALUES (NULL, '1990-10-02', 'damien.leroux@formation.com', 'Leroux', 'Damien');
INSERT INTO Stagiaire (id, dateDeNaissance, email, nom, prenom) VALUES (NULL, '1980-02-04', 'stagiaire7@formation.com', 'Durant', 'Fanny');
INSERT INTO Stagiaire (id, dateDeNaissance, email, nom, prenom) VALUES (NULL, '1981-07-24', 'stagiaire8@formation.com', 'Umlil', 'Mehdi');
INSERT INTO Stagiaire (id, dateDeNaissance, email, nom, prenom) VALUES (NULL, '1985-08-24', 'stagiaire9@formation.com', 'Sanchez', 'Jose');
INSERT INTO Stagiaire (id, dateDeNaissance, email, nom, prenom) VALUES (NULL, '1976-12-14', 'francis.martinez@formation.com', 'Martinez', 'Francis');
INSERT INTO Stagiaire (id, dateDeNaissance, email, nom, prenom) VALUES (NULL, '1970-03-10', 'stagiaire11@formation.com', 'Lepetit', 'Paul');
INSERT INTO Stagiaire (id, dateDeNaissance, email, nom, prenom) VALUES (NULL, '1990-02-13', 'stagiaire12@formation.com', 'Leroux', 'Jacques');

INSERT INTO Formateur (id, entreprise, nom, poste, prenom) VALUES (NULL, 'e-formation', 'Lebon', 'formateur', 'Julien');
INSERT INTO Formateur (id, entreprise, nom, poste, prenom) VALUES (NULL, 'e-formation', 'Bouchon', 'formateur', 'Alain');
INSERT INTO Formateur (id, entreprise, nom, poste, prenom) VALUES (NULL, 'formation pour tous', 'Boisvert', 'formateur', 'Luc');


INSERT INTO Formation (id, duree, intitule, niveau) VALUES (NULL, '20', 'Java/JEE', '8');
INSERT INTO Formation (id, duree, intitule, niveau) VALUES (NULL, '2', 'Hibernate', '5');
INSERT INTO Formation (id, duree, intitule, niveau) VALUES (NULL, '10', 'PHP', '3');
INSERT INTO Formation (id, duree, intitule, niveau) VALUES (NULL, '25', 'C++', '10');

INSERT INTO Session (id, dateDeDebut,dateDeFin,formation_id) VALUES (NULL, '2015-04-01', '2015-04-28', '1');
INSERT INTO Session (id, dateDeDebut,dateDeFin,formation_id) VALUES (NULL, '2015-05-18', '2015-06-01', '3');


INSERT INTO Session_Stagiaire (sessions_id, stagiaires_id) VALUES (1,1);
INSERT INTO Session_Stagiaire (sessions_id, stagiaires_id) VALUES (1,2);
INSERT INTO Session_Stagiaire (sessions_id, stagiaires_id) VALUES (1,3);
INSERT INTO Session_Stagiaire (sessions_id, stagiaires_id) VALUES (1,4);
INSERT INTO Session_Stagiaire (sessions_id, stagiaires_id) VALUES (1,5);
INSERT INTO Session_Stagiaire (sessions_id, stagiaires_id) VALUES (2,6);
INSERT INTO Session_Stagiaire (sessions_id, stagiaires_id) VALUES (2,7);
INSERT INTO Session_Stagiaire (sessions_id, stagiaires_id) VALUES (2,8);
INSERT INTO Session_Stagiaire (sessions_id, stagiaires_id) VALUES (2,9);
INSERT INTO Session_Stagiaire (sessions_id, stagiaires_id) VALUES (2,10);
INSERT INTO Session_Stagiaire (sessions_id, stagiaires_id) VALUES (2,11);
INSERT INTO Session_Stagiaire (sessions_id, stagiaires_id) VALUES (2,12);

INSERT INTO Formateur_Formation (formateurs_id, formations_id) VALUES (1,1);
INSERT INTO Formateur_Formation (formateurs_id, formations_id) VALUES (2,3);

INSERT INTO Evaluation (session_id, stagiaire_id, dateCourante, note) VALUES ('1', '1', '2015-05-13', '3');
INSERT INTO Evaluation (session_id, stagiaire_id, dateCourante, note) VALUES ('1', '2', '2015-05-13', '4');
INSERT INTO Evaluation (session_id, stagiaire_id, dateCourante, note) VALUES ('1', '3', '2015-05-13', '6');
INSERT INTO Evaluation (session_id, stagiaire_id, dateCourante, note) VALUES ('1', '4', '2015-05-13', '8');
INSERT INTO Evaluation (session_id, stagiaire_id, dateCourante, note) VALUES ('1', '5', '2015-05-13', '8');
INSERT INTO Evaluation (session_id, stagiaire_id, dateCourante, note) VALUES ('2', '6', '2015-05-13', '8');
INSERT INTO Evaluation (session_id, stagiaire_id, dateCourante, note) VALUES ('2', '7', '2015-05-13', '7');
INSERT INTO Evaluation (session_id, stagiaire_id, dateCourante, note) VALUES ('2', '8', '2015-05-13', '5');
INSERT INTO Evaluation (session_id, stagiaire_id, dateCourante, note) VALUES ('2', '9', '2015-05-13', '8');
INSERT INTO Evaluation (session_id, stagiaire_id, dateCourante, note) VALUES ('2', '10', '2015-05-13', '8');
INSERT INTO Evaluation (session_id, stagiaire_id, dateCourante, note) VALUES ('2', '11', '2015-05-13', '8');
INSERT INTO Evaluation (session_id, stagiaire_id, dateCourante, note) VALUES ('2', '12', '2015-05-13', '8');

SET foreign_key_checks = 1;
