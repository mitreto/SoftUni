package com.hospital.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visitations")
public class Visitation extends BaseEntityId {

    private LocalDateTime date;
    private String comment;
    private Patient patient;

    public Visitation() {
    }

    @Column(name = "date")
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Column(name = "comments")
    public String getComment() {
        return comment;
    }

    public void setComment(String comments) {
        this.comment = comments;
    }

    @ManyToOne
    @JoinColumn(name = "visitation_id", referencedColumnName = "id")
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
