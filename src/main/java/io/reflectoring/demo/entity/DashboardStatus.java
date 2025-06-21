package io.reflectoring.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dashboard_status")
public class DashboardStatus {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullname;
    private String userId;
    private String status;
    private String notes;

    // Constructors, getters and setters
    public DashboardStatus() {}
    public DashboardStatus(String status, String notes, String fullname, String userId) {
        this.status = status;
        this.notes = notes;
        this.fullname = fullname;
        this.userId = userId;
    }

    public Long getId() { return id; }
    public void setFullName(String fullName) { this.fullname = fullName; }
    public String getFullName() { return fullname; }
    public String getUserId() { return userId; }
    public String getStatus() { return status; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
