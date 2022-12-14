package com.example.picallti.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Entity
public class Offre {
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
    private Integer id;
    private Integer imageId;
    private String titre;
    private String description;
    private String localisation;
    private float prix;
    private LocalTime time;
    private String operation;

    @ManyToOne
    private User user;
    @ManyToOne
    private Vehicule vehicule;
    private String url;
    private LocalDate LocalDateTime;

    public Offre(Integer id, Integer imageId, String titre, String description, String localisation, float prix, LocalTime time, String operation, User user,Vehicule vehicule,LocalDate LocalDateTime) {
        this.id = id;
        this.imageId = imageId;
        this.titre = titre;
        this.description = description;
        this.localisation = localisation;
        this.prix = prix;
        this.time = time;
        this.operation = operation;
        this.user = user;
        this.LocalDateTime = LocalDateTime;
        this.vehicule = vehicule;
    }

    public Offre( Integer imageId, String titre, String description, String localisation, float prix, LocalTime time, String operation, User user,Vehicule vehicule,LocalDate LocalDateTime) {
        this.imageId = imageId;
        this.titre = titre;
        this.description = description;
        this.localisation = localisation;
        this.prix = prix;
        this.time = time;
        this.operation = operation;
        this.user = user;
        this.vehicule = vehicule;
        this.LocalDateTime = LocalDateTime;
    }

    public Offre(int id, String titre, String description, String operation, float prix, String url, User owner, Vehicule vehicule) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.operation = operation;
        this.prix = prix;
        this.user = owner;
        this.vehicule = vehicule;
        this.url = url;
    }

    public Offre(String titre, String description, String operation, float prix, String url, User owner, Vehicule vehicule) {
        this.titre = titre;
        this.description = description;
        this.operation = operation;
        this.prix = prix;
        this.user = owner;
        this.vehicule = vehicule;
        this.url = url;
    }
    public Offre(String titre, String operation,float prix,String description,  String url, User owner, Vehicule vehicule) {
        this.titre = titre;
        this.description = description;
        this.operation = operation;
        this.prix = prix;
        this.user = owner;
        this.vehicule = vehicule;
        this.url = url;
    }
    public Offre(String titre, String description, float prix, String url ){
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.url = url;
    }

    public Offre() {

    }

    public Integer getId() {
        return id;
    }

    public Integer getImageId() {
        return imageId;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getLocalisation() {
        return localisation;
    }

    public float getPrix() {
        return prix;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public LocalDate getLocalDateTime() {
        return LocalDateTime;
    }

    public void setLocalDateTime(LocalDate LocalDateTime) {
        this.LocalDateTime = LocalDateTime;
    }

    @Override
    public String toString() {
        return "Offre{" +
                "id=" + id +
                ", imageId=" + imageId +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", localisation='" + localisation + '\'' +
                ", prix='" + prix + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
