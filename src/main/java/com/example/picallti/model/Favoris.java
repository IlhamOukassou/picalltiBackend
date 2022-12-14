package com.example.picallti.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity

public class Favoris {
    @Id
    @SequenceGenerator(
            name = "picallti_sequence",
            sequenceName = "picallti_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "picallti_sequence"
    )
    private int id;

    @ManyToOne
    private User user;
    @ManyToOne
    private Offre offre;
    private LocalDateTime localDateTime;

    public Favoris(int id, User user, Offre offre) {
        this.id = id;
        this.user = user;
        this.offre = offre;
    }
    public Favoris( User user, Offre offre) {
        this.user = user;
        this.offre = offre;
    }
    public Favoris (Offre offre){
        this.offre = offre;
    }

    public Favoris() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime LocalDateTime) {
        this.localDateTime = LocalDateTime;
    }
}
