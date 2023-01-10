package com.example.picallti.model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long imageId;
    private String titre;
    private String description;
    private String localisation;
    private float prix;
    private String time;
    private String operation;
    private String ville;

    private String imageName;



    @ManyToOne
    @JoinColumn(name = "user",referencedColumnName = "id")
    private User user;
    @ManyToOne
    private Vehicule vehicule;

    //private String url;
    private String locaLDate;

    public Offre(Integer id, Long imageId, String titre, String description, String localisation, float prix, String time, String operation, User user,Vehicule vehicule,String locaLDate,String ville) {
        this.id = id;
        this.imageId = imageId;
        this.titre = titre;
        this.description = description;
        this.localisation = localisation;
        this.prix = prix;
        this.time = time;
        this.operation = operation;
        this.user = user;
        this.locaLDate = locaLDate;
        this.vehicule = vehicule;
        this.ville = ville;

    }

    public Offre( Long imageId, String titre, String description, String localisation, float prix, String time, String operation, User user,Vehicule vehicule,String locaLDate,String ville) {
        this.imageId = imageId;
        this.titre = titre;
        this.description = description;
        this.localisation = localisation;
        this.prix = prix;
        this.time = time;
        this.operation = operation;
        this.user = user;
        this.vehicule = vehicule;
        this.locaLDate = locaLDate;
        this.ville = ville;

    }

    public Offre(int id, String titre, String description, String operation, float prix, User owner, Vehicule vehicule,String ville) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.operation = operation;
        this.prix = prix;
        this.user = owner;
        this.vehicule = vehicule;
        this.ville = ville;

    }

    public Offre(String titre, String description, String operation, float prix, User owner, Vehicule vehicule,String ville) {
        this.titre = titre;
        this.description = description;
        this.operation = operation;
        this.prix = prix;
        this.user = owner;
        this.vehicule = vehicule;
        this.ville = ville;

    }
    public Offre(String titre, String operation,float prix,String description, User owner, Vehicule vehicule,String ville) {
        this.titre = titre;
        this.description = description;
        this.operation = operation;
        this.prix = prix;
        this.user = owner;
        this.vehicule = vehicule;
        this.ville = ville;

    }
    public Offre(String titre, String description, float prix,String ville ){
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.ville = ville;
    }

    public Offre() {

    }

    public Integer getId() {
        return id;
    }

    public Long getImageId() {
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

    public String getTime() {
        return time;
    }

    public void setImageId(Long imageId) {
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

    public void setTime(String time) {
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

    public String getlocaLDate() {
        return locaLDate;
    }

    public void setlocaLDate(String locaLDate) {
        this.locaLDate = locaLDate;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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


    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    /*public String getLocaLDate() {
        return locaLDate;
    }

    public void setLocaLDate(String locaLDate) {
        this.locaLDate = locaLDate;
    }*/
}
