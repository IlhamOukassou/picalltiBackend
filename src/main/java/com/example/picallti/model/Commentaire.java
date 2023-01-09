package com.example.picallti.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String commentaire;
    @ManyToOne
    private User user;
    @ManyToOne
    private Offre offre;
    private String time;
    private String LocalDateTime;

    public Commentaire(int id, String commentaire, User user, Offre offre, String time, String localDateTime) {
        this.id = id;
        this.commentaire = commentaire;
        this.user = user;
        this.offre = offre;
        this.time = time;
        LocalDateTime = localDateTime;
    }

    public Commentaire() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocalDateTime() {
        return LocalDateTime;
    }

    public void setLocalDateTime(String localDateTime) {
        LocalDateTime = localDateTime;
    }

    public Commentaire(String commentaire, User user, Offre offre, LocalTime now, LocalDate now1) {
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "id=" + id +
                ", commentaire='" + commentaire + '\'' +
                ", user=" + user +
                ", offre=" + offre +
                ", time='" + time + '\'' +
                ", LocalDateTime='" + LocalDateTime + '\'' +
                '}';
    }
}
