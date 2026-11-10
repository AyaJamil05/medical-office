-- Données fictives pour une démonstration locale.
-- À exécuter après schema.sql sur une base de test vide.
USE medical_office;

START TRANSACTION;

INSERT INTO admins (username, password)
VALUES ('admin', 'password');

INSERT INTO doctors (username, password, name)
VALUES ('medecin_demo', 'password', 'Dr Médecin Démo');
SET @demo_doctor_id = LAST_INSERT_ID();

INSERT INTO patients (username, password, name, dob, email)
VALUES ('patient_demo', 'password', 'Patient Démo', '1995-04-12', 'patient-demo@example.com');
SET @demo_patient_id = LAST_INSERT_ID();

INSERT INTO appointments (patient_id, doctor_id, date, time)
VALUES (@demo_patient_id, @demo_doctor_id, CURRENT_DATE + INTERVAL 7 DAY, '10:00:00');

INSERT INTO medical_records (patient_id, `Diagnostiques`, `Médications`, `Notes du médecin`)
VALUES (@demo_patient_id, 'Consultation fictive', 'Aucune', 'Dossier de démonstration.');

COMMIT;
