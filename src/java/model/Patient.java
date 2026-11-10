package model;

import java.time.LocalDate;

public class Patient {
    private Long id;
    private String username;
    private String password;
    private String name;
    private LocalDate dob;
    private String phone;
    private String email;

    public Patient() {
    }
    public Patient(String username, String password, String name,LocalDate dob, String phone, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
    }
    public Patient(Long id, String username, String password, String name, LocalDate dob, String phone, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Le mot de passe ne peut pas être vide.");
        }
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

