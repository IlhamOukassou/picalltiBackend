package com.example.picallti.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int note;

    @ManyToOne
    private User user;
    @ManyToOne
    private Offre offre;
    private LocalDateTime LocalDateTime;

    public Note(int id, int note, User user, Offre offre) {
        this.id = id;
        this.note = note;
        this.user = user;
        this.offre = offre;
        //this.LocalDateTime = LocalDateTime;
    }
    public Note(int note, User user, Offre offre) {
        this.note = note;
        this.user = user;
        this.offre = offre;
        //this.LocalDateTime = LocalDateTime;
    }

    public Note() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Offre getOffre() {
        return offre;
    }

    public void setOffre(Offre offre) {
        this.offre = offre;
    }

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime;
    }

    public void setLocalDateTime(LocalDateTime LocalDateTime) {
        this.LocalDateTime = LocalDateTime;
    }
}
