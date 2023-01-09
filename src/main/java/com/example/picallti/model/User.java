package com.example.picallti.model;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private String genre;

    @Column(unique = true)
    private String email;
    private int phone;
    private String password;
    private Long imageId;
    private String bio;
    private String role;


    public User(int id, String nom, String prenom, String genre, String email, int phone, String password, Long imageId, String bio, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.imageId = imageId;
        this.bio = bio;
        this.role = role;
    }
    public User(String nom, String prenom, String genre, String email, int phone, String password, Long imageId, String bio, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.imageId = imageId;
        this.bio = bio;
        this.role = role;
    }

    public User(String nom, String prenom, String genre, String email, int phone, String password, Long imageId, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.imageId = imageId;
        this.role = role;
    }

    public User(String nom, String prenom, String genre, String email, int phone, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
    public User(String nom, String prenom, String genre, String email, int phone, String bio, Long imageId) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.email = email;
        this.phone = phone;
        this.bio = bio;
        this.imageId = imageId;
    }

    public User(String nom, String prenom, String email, int phone, String bio ,Long imageId) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.email = email;
        this.phone = phone;
        this.bio = bio;
        this.imageId= imageId;
    }
    public User() {

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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
