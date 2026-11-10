package dao;

import model.MedicalRecord;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utils.DatabaseConnection;

public class MedicalRecordDAO {
    public MedicalRecord getMedicalRecordById(int id) throws SQLException {
        String query = "SELECT * FROM medical_records WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    MedicalRecord record = new MedicalRecord();
                    record.setId(resultSet.getInt("id"));
                    record.setPatientId(resultSet.getInt("patient_id"));
                    record.setDiagnostiques(resultSet.getString("Diagnostiques"));
                    record.setMedications(resultSet.getString("Médications"));
                    record.setNotesDuMedecin(resultSet.getString("Notes du médecin"));
                    return record;
                }
            }
        }
        return null; 
    }

    public List<MedicalRecord> getMedicalRecordsByPatientId(int patientId) throws SQLException {
        List<MedicalRecord> records = new ArrayList<>();
        String query = "SELECT * FROM medical_records WHERE patient_id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            
            pstmt.setInt(1, patientId);
            ResultSet resultSet = pstmt.executeQuery();
            
            while (resultSet.next()) {
                MedicalRecord record = new MedicalRecord(
                        resultSet.getInt("id"),
                        resultSet.getInt("patient_id"),
                        resultSet.getString("Diagnostiques"),
                        resultSet.getString("Médications"),
                        resultSet.getString("Notes du médecin"),
                        resultSet.getString("created_at"),
                        resultSet.getString("last_update")
                );
                records.add(record);
            }
        }
        return records;
    }
    
    public void createMedicalRecord(MedicalRecord record) throws SQLException {
        String query = "INSERT INTO medical_records (patient_id, Diagnostiques, Médications, `Notes du médecin`, created_at, last_update) VALUES (?, ?, ?, ?, NOW(), NOW())";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, record.getPatientId());
            pstmt.setString(2, record.getDiagnostiques());
            pstmt.setString(3, record.getMedications());
            pstmt.setString(4, record.getNotesDuMedecin());
            pstmt.executeUpdate();
        }
    }
    
    public void updateMedicalRecord(MedicalRecord record) throws SQLException {
        String query = "UPDATE medical_records SET Diagnostiques = ?, Médications  = ?, `Notes du médecin` = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, record.getDiagnostiques());
            pstmt.setString(2, record.getMedications());
            pstmt.setString(3, record.getNotesDuMedecin());
            pstmt.setInt(4, record.getId());

            pstmt.executeUpdate();
        }
    }
    
    public void deleteMedicalRecord(int recordId) throws SQLException {
        String query = "DELETE FROM medical_records WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, recordId);
            pstmt.executeUpdate();
        }
    }

}


