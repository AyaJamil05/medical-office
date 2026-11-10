package dao;

import model.Doctor;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
  // Récupérer un médecin par son nom d'utilisateur
    public Doctor getDoctorByUsername(String username) {
        String query = "SELECT * FROM doctors WHERE username = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Doctor(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }  
    
    // Récupérer un médecin par son ID
    public Doctor getDoctorById(int id) {
        String query = "SELECT * FROM doctors WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return mapRowToDoctor(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Doctor> getAllDoctors() throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctors";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                doctors.add(mapRowToDoctor(rs));
            }
        }
        return doctors;
    }
    
    // Méthode pour filtrer les médecins par nom
    public List<Doctor> getDoctorsByName(String name) throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctors WHERE name LIKE ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, "%" + name + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    doctors.add(mapRowToDoctor(rs));
                }
            }
        }
        return doctors;
    }
    
    public void createDoctor(Doctor doctor) throws SQLException {
        String query = "INSERT INTO doctors (username, password, name, phone) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, doctor.getUsername());
            stmt.setString(2, doctor.getPassword());
            stmt.setString(3, doctor.getName());
            stmt.setString(4, doctor.getPhone());
            stmt.executeUpdate();
        }
    }

    
    public void updateDoctor(Doctor doctor) throws SQLException {
        String query = "UPDATE doctors SET username = ?, name = ?, phone = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, doctor.getUsername());
            stmt.setString(2, doctor.getName());
            stmt.setString(3, doctor.getPhone());
            stmt.setInt(4, doctor.getId());
            stmt.executeUpdate();
        }
    }


    // Méthode pour supprimer un médecin par ID
    public void deleteDoctor(int id) throws SQLException {
        String query = "DELETE FROM doctors WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Méthode utilitaire pour mapper une ligne de ResultSet en objet Doctor
    private Doctor mapRowToDoctor(ResultSet resultSet) throws SQLException {
        return new Doctor(
                resultSet.getInt("id"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("name"),
                resultSet.getString("phone")
        );
    }
    
    // Compter le nombre total de médecins
    public int countDoctors() throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM doctors";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }
}
