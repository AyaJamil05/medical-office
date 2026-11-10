package model;

public class MedicalRecord {
    private int id;
    private int patientId;
    private String diagnostiques;
    private String medications;
    private String notesDuMedecin;
    private String createdAt;
    private String lastUpdate;

    // Constructeur par défaut
    public MedicalRecord() {}

    // Constructeur avec tous les champs
    public MedicalRecord(int id, int patientId, String diagnostiques, String medications, String notesDuMedecin, String createdAt, String lastUpdate) {
        this.id = id;
        this.patientId = patientId;
        this.diagnostiques = diagnostiques;
        this.medications = medications;
        this.notesDuMedecin = notesDuMedecin;
        this.createdAt = createdAt;
        this.lastUpdate = lastUpdate;
    }

    // Constructeur sans les champs auto-générés
    public MedicalRecord(int patientId, String diagnostiques, String medications, String notesDuMedecin) {
        this.patientId = patientId;
        this.diagnostiques = diagnostiques;
        this.medications = medications;
        this.notesDuMedecin = notesDuMedecin;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getDiagnostiques() {
        return diagnostiques;
    }

    public void setDiagnostiques(String diagnostiques) {
        this.diagnostiques = diagnostiques;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public String getNotesDuMedecin() {
        return notesDuMedecin;
    }

    public void setNotesDuMedecin(String notesDuMedecin) {
        this.notesDuMedecin = notesDuMedecin;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    // Méthode toString
    @Override
    public String toString() {
        return "MedicalRecord{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", diagnostiques='" + diagnostiques + '\'' +
                ", medications='" + medications + '\'' +
                ", notesDuMedecin='" + notesDuMedecin + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                '}';
    }
}
