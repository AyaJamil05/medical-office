package dao;

import model.Patient;
import utils.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    public Patient getPatientByUsername(String username) {
        Patient patient = null;
        String query = "SELECT * FROM patients WHERE username = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, username);
            ResultSet resultSet = pstmt.executeQuery();
            
            if (resultSet.next()) {
                patient = new Patient(
                    resultSet.getLong("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("name"),
                    resultSet.getDate("dob").toLocalDate(),
                    resultSet.getString("phone"),
                    resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return patient;
    }

    public List<Patient> getPatientsByName(String name) throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patients WHERE name LIKE ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, "%" + name + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    patients.add(mapRowToPatient(rs));
                }
            }
        }
        return patients;
    }

    public Patient getPatientById(Long id) throws SQLException {
        String query = "SELECT * FROM patients WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToPatient(rs);
                }
            }
        }
        return null;
    }

    public void createPatient(Patient patient) throws SQLException {
        String checkQuery = "SELECT COUNT(*) FROM patients WHERE email = ? OR username = ?";
        String insertQuery = "INSERT INTO patients (name, dob, phone, email, username, password) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
             PreparedStatement insertStmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

            // Vérification des doublons
            checkStmt.setString(1, patient.getEmail());
            checkStmt.setString(2, patient.getUsername());
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    throw new SQLException("Un patient avec cet email ou nom d'utilisateur existe déjà.");
                }
            }

            // Insertion du nouveau patient
            insertStmt.setString(1, patient.getName());
            insertStmt.setDate(2, Date.valueOf(patient.getDob()));
            insertStmt.setString(3, patient.getPhone());
            insertStmt.setString(4, patient.getEmail());
            insertStmt.setString(5, patient.getUsername());
            insertStmt.setString(6, patient.getPassword());
            insertStmt.executeUpdate();

            try (ResultSet generatedKeys = insertStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    patient.setId(generatedKeys.getLong(1));
                }
            }
        }
    }

    public List<Patient> getAllPatients() throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patients";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                patients.add(mapRowToPatient(rs));
            }
        }
        return patients;
    }

    public void updatePatient(Patient patient) throws SQLException {
        String query = "UPDATE patients SET name = ?, dob = ?, phone = ?, email = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, patient.getName());
            pstmt.setDate(2, Date.valueOf(patient.getDob()));
            pstmt.setString(3, patient.getPhone());
            pstmt.setString(4, patient.getEmail());
            pstmt.setLong(5, patient.getId());
            pstmt.executeUpdate();
        }
    }

    public void deletePatient(Long id) throws SQLException {
        String query = "DELETE FROM patients WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }

    private Patient mapRowToPatient(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String username = rs.getString("username");
        String password = rs.getString("password");
        String name = rs.getString("name");
        LocalDate dob = rs.getDate("dob").toLocalDate();
        String phone = rs.getString("phone");
        String email = rs.getString("email");

        return new Patient(id, username, password, name, dob, phone, email);
    }

    public int countPatients() throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM patients";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }
}
