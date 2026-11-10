package dao;

import model.Appointment;
import model.Doctor;
import model.Patient;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import utils.DatabaseConnection;


public class AppointmentDAO {
    public void createAppointment(Appointment appointment) throws SQLException {
        String query = "INSERT INTO appointments (patient_id, doctor_id, date, time) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setLong(1, appointment.getPatient().getId());
            pstmt.setInt(2, appointment.getDoctor().getId());
            pstmt.setDate(3, Date.valueOf(appointment.getDate()));
            pstmt.setTime(4, Time.valueOf(appointment.getTime()));

            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    appointment.setId(generatedKeys.getLong(1));
                }
            }
        }
    }

    public Appointment getAppointmentById(Long id) throws SQLException {
        String query = "SELECT * FROM appointments WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setLong(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToAppointment(rs, connection);
                }
            }
        }
        return null;
    }

    public List<Appointment> getAppointmentsByDate(LocalDate filterDate) throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appointments WHERE date = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setDate(1, Date.valueOf(filterDate));

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    appointments.add(mapRowToAppointment(rs, connection));
                }
            }
        }
        return appointments;
    }

    public List<Appointment> getAllAppointments() throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appointments Order By date";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                appointments.add(mapRowToAppointment(rs, connection));
            }
        }
        return appointments;
    }
    public void updateAppointment(Appointment appointment) throws SQLException {
        String query = "UPDATE appointments SET patient_id = ?, doctor_id = ?, date = ?, time = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setLong(1, appointment.getPatient().getId());
            pstmt.setInt(2, appointment.getDoctor().getId());
            pstmt.setDate(3, Date.valueOf(appointment.getDate()));
            pstmt.setTime(4, Time.valueOf(appointment.getTime()));
            pstmt.setLong(5, appointment.getId());

            pstmt.executeUpdate();
        }
    }
    public void deleteAppointment(Long id) throws SQLException {
        String query = "DELETE FROM appointments WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }
    private Appointment mapRowToAppointment(ResultSet rs, Connection connection) throws SQLException {
        Long id = rs.getLong("id");
        Long patientId = rs.getLong("patient_id");
        int doctorId = rs.getInt("doctor_id");
        LocalDate date = rs.getDate("date").toLocalDate();
        LocalTime time = rs.getTime("time").toLocalTime();

        // Fetch related Patient and Doctor objects
        Patient patient = new PatientDAO().getPatientById(patientId);
        Doctor doctor = new DoctorDAO().getDoctorById(doctorId);

        return new Appointment(id, patient, doctor, date, time);
    }   
    public boolean isTimeReserved(int doctorId, LocalDate date, LocalTime time) throws SQLException {
        String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND date = ? AND time = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, doctorId);
            pstmt.setDate(2, Date.valueOf(date));
            pstmt.setTime(3, Time.valueOf(time));
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public int countAppointments() throws SQLException {
        String query = "SELECT COUNT(*) AS total FROM appointments";
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
