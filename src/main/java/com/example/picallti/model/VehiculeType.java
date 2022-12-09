package com.example.picallti.model;


import jakarta.persistence.*;

@Entity
public class VehiculeType {
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
    private String nom;
    private String url;

    public VehiculeType() {

    }

    public VehiculeType(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    public VehiculeType(int id, String nom,String url) {
        this.id = id;
        this.nom = nom;
    }
    public VehiculeType(String nom) {
        this.nom = nom;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
